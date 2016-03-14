package file_options;

import main.GUIBuilder;

import java.io.*;

/**
 * Created by corey on 3/11/16.
 */
public class WriteFile
{
    private static String fileName;
    public WriteFile(File name)
    {
        fileName = name.toString();
    }

    public static void writeFile()
    {
        String text = GUIBuilder.getText();
        try
        {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            GUIBuilder.textArea.write(bufferedWriter);
            bufferedWriter.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch (IOException ex)
        {
            System.out.println("Error writing file '" + fileName + "'");
        }
    }
}
