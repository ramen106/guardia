import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.animation.PauseTransition;

public class Controller implements Initializable {

    int MIN_PASSWORD_LENGTH = 8;
    int MAX_PASSWORD_LENGTH = 64;

    int MIN_PASSWORD_COUNT = 1;
    int MAX_PASSWORD_COUNT = 10000;

    @FXML
    private Spinner<Integer> passwordCount;
 
    @FXML
    private Spinner<Integer> passwordLength;
    
    @FXML
    private ListView<String> passwordListView;

    @FXML
    private Label copyAlert;
    
    @FXML
    private Label exportAlert;

    @FXML
    private CheckBox numberingCheck;
    // This is what actually holds the item, list view listens to it and draws changes/passwords that are added
    private ObservableList<String> passwords = FXCollections.observableArrayList();

    int passwordsToGenerate;
    
    int passwordSize;

    private PauseTransition copyTimer;

    private PauseTransition exportTimer;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {        
        // Binding listview to the observable list
        passwordListView.setItems(passwords);
        
        // If double left click a password, put it in clipboard stream and push it to system clipboard (copy it)
        passwordListView.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2)
            {
                String selectedPassword = passwordListView.getSelectionModel().getSelectedItem();
                if(selectedPassword != null)
                {
                    // Get the password minus the trailing number, period and spaces by trimming after period
                    String actualPassword = selectedPassword.substring(selectedPassword.indexOf(".") + 1).trim();
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(actualPassword);
                    clipboard.setContent(content);
                    copyAlert.setText("Password copied!");
                    copyAlert.setVisible(true);

                    // Set a timer for 2 seconds
                    if(copyTimer != null)
                    {
                        copyTimer.stop();
                    }

                    copyTimer = new PauseTransition(Duration.seconds(1));
                    copyTimer.setOnFinished(event2 -> copyAlert.setVisible(false));

                    // Start the timer, the text will disappear after 2 seconds
                    copyTimer.play();
                }
            }
            });
            
        // Handle the spinners for the password length and count, 
        // setting their minimum and max values as 8-64 and 1-10000 respectively
        // default values are 16 for length, 5 for password count
        SpinnerValueFactory<Integer> passwordLengthFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH);
        SpinnerValueFactory<Integer> passwordCountFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_PASSWORD_COUNT, MAX_PASSWORD_COUNT);
        passwordLengthFactory.setValue(16);
        passwordCountFactory.setValue(5);

        // assigning the factories to the spinners
        passwordCount.setValueFactory(passwordCountFactory);
        passwordLength.setValueFactory(passwordLengthFactory);


    }

    // the methods responsible for generating and exporting, binding them to the buttons in the FXML/scene builder
   @FXML
    public void generate(ActionEvent e)
    {
        passwords.clear();
        passwordsToGenerate = passwordCount.getValue();
        passwordSize = passwordLength.getValue();

        for(int i = 0; i < passwordsToGenerate; i++)
        {
            passwords.add((i+1) + ".     " + Generator.generatePassword(passwordSize));
        }
    }

    @FXML
    public void export(ActionEvent e)
    {
        boolean printWithNumbering = numberingCheck.isSelected();

        if(Exporter.exportPasswords(passwords, printWithNumbering))
        {
            if(exportTimer != null)
            {
                exportTimer.stop();
            }
            
            exportAlert.setManaged(true);
            exportAlert.setVisible(true);
            exportTimer = new PauseTransition(Duration.seconds(1));
            exportTimer.setOnFinished(event -> exportAlert.setVisible(false));
            exportTimer.play();
        }
        
    } 
}
