import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class Controller implements Initializable {

    @FXML
    private Spinner<Integer> passwordCount;
 
    @FXML
    private Spinner<Integer> passwordLength;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        // Handle the spinners for the password length and count, 
        // setting their minimum and max values as 8-32 and 1-100 respectively
        // default values are 16 for length, 5 for password count
        SpinnerValueFactory<Integer> passwordLengthFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 32);
        SpinnerValueFactory<Integer> passwordCountFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
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
        System.out.println("Generated");
    }

    @FXML
    public void export(ActionEvent e)
    {
        System.out.println("Exported");

    } 
}
