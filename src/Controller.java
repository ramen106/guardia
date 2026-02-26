import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class Controller implements Initializable {

    @FXML
    private Spinner<Integer> passwordCount;
 
    @FXML
    private Spinner<Integer> passwordLength;
    
    
    @FXML
    private ListView<String> passwordListView;

    // This is what actually holds the item, list view listens to it and draws changes/passwords that are added
    private ObservableList<String> passwords = FXCollections.observableArrayList();

    int passwordsToGenerate;
    int passwordSize;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        // Binding listview to the observable list
        passwordListView.setItems(passwords);
        
        // If double left click a password, put it in clipboard stream and push it to system clipboard (copy it)
        passwordListView.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2)
            {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(passwordListView.getSelectionModel().getSelectedItem());
                clipboard.setContent(content);
                // TODO: display "Copied!"
            }
            });
        // Handle the spinners for the password length and count, 
        // setting their minimum and max values as 8-32 and 1-100 respectively
        // default values are 16 for length, 5 for password count
        SpinnerValueFactory<Integer> passwordLengthFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 64);
        SpinnerValueFactory<Integer> passwordCountFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000);
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
            passwords.add(Generator.generatePassword(passwordSize));
        }
    }

    @FXML
    public void export(ActionEvent e)
    {
        System.out.println("Exported");

    } 
}
