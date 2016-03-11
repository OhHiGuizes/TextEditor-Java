package file_options;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by corey on 3/11/16.
 */
public class ReadFile {
    private String fileName;
    public ReadFile(String name){
        fileName = name;
    }

    public String readFile(){
        String line = null;
        String fileData = "";
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null)
            {
                fileData += line + "\n";
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex)
        {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return fileData;
    }
}
