package file_options;

import main.TextEditor;

import java.io.*;

/**
 * Created by corey on 3/11/16.
 */
public class ReadFile
{
    private String fileName;
    public ReadFile(String name)
    {
        fileName = name;
    }

    public String readFile()
    {
        StringBuilder line = new StringBuilder();
        try
        {
            String bufferedLine = null;
            BufferedReader buffer = new BufferedReader(new FileReader(new File(TextEditor.getFileName())));
            while ((bufferedLine = buffer.readLine()) != null)
            {
                line.append(bufferedLine);
                line.append("\n");
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex)
        {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return line.toString();
    }
}
