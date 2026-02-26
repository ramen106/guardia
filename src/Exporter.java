import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class Exporter
{
    public static boolean exportPasswords(List<String> passwords, boolean printWithNumbering)
    {
        // Make filechooser instance and find the path, set default file name to passwords.txt
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setInitialFileName("passwords.txt");
        
            // Set the "save as type" to .txt
        ExtensionFilter extension = new ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(extension);
        
            // Prompt the user for a location/file and fetch the path
        File selectedFile = fileChooser.showSaveDialog(null);

        if(selectedFile == null)
        {
            return false;
        }

        String path = selectedFile.getAbsolutePath();

        if(!path.endsWith(".txt"))
        {
            path += ".txt";
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path)))
        {
            for(String password : passwords)
            {
                String actualPassword = password.substring(password.indexOf(".") + 1).trim();

                if(printWithNumbering == true)
                {
                    writer.write(password);
                }

                else
                {
                    writer.write(actualPassword);
                }
                
                writer.newLine();
            }

            return true;
        }

        catch (IOException e)
        {
            System.out.println("Failure to write file");
            e.printStackTrace();
            return false;
        }
    }
}
