package file_options;

import main.GUIBuilder;
import main.TextEditor;

import java.io.*;

/**
 * Created by corey on 3/11/16.
 */
public class ReadFile
{
    protected String fileName;
    public ReadFile(File name)
    {
        fileName = name.toString();
    }

    public void readFile() {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(new File(fileName)));
            GUIBuilder.textArea.read(buffer, null);
            buffer.close();
            GUIBuilder.textArea.requestFocus();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }
}
