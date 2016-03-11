package file_options;

import main.GUIBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by corey on 3/11/16.
 */
public class WriteFile
{
    private static String fileName;
    public WriteFile(String name)
    {
        fileName = name;
    }

    public static void writeFile()
    {
        String text = GUIBuilder.getText();
        try
        {
            File file = new File(fileName);
            PrintWriter printMe = new PrintWriter(file);
            printMe.write(text);
            printMe.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Unable to open file '" + fileName + "'");
        }
    }
}
