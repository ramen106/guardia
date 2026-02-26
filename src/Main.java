import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try
        {
            // loading the gui and setting up the stages, icon and app title
        Parent root = FXMLLoader.load(getClass().getResource("/GUI.fxml"));
        Scene scene = new Scene(root, Color.GRAY);
        String iconPath = getClass().getResource("/icon.png").toExternalForm();
        primaryStage.getIcons().add(new Image(iconPath));
        primaryStage.setTitle("Guardia");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}