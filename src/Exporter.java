import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javafx.collections.ObservableList;


public class Exporter {

    public static void exportPasswords(ObservableList<String> passwords)
    {
        // Make filechooser instance and find the path, set default file name to passwords.txt
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setSelectedFile(new File("passwords.txt"));
        int response = fileChooser.showSaveDialog(null);
        String path = fileChooser.getSelectedFile().getAbsolutePath();

        // If user successfully chose path, check if their chosen file is a txt file, if not append .txt
        // Then write every item of the observablelist of passwords into the file
        if(response == JFileChooser.APPROVE_OPTION)
        {
            if(!path.endsWith(".txt"))
            {
                path += ".txt";
            }

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(path)))
            {
                writer.write("hi guys");
            }

            catch (IOException e)
            {
                System.out.println("Failure to write file");
                e.printStackTrace();
            }
        }
    }
}
