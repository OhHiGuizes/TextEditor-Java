package actions;

import file_options.ReadFile;
import main.GUIBuilder;
import main.TextEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by corey on 3/11/16.
 */
public class OpenListener implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        int returnVal = GUIBuilder.fc.showOpenDialog(null);

        if (returnVal == GUIBuilder.fc.APPROVE_OPTION)
        {
            File file = GUIBuilder.fc.getSelectedFile();
//          Process p = Runtime.getRuntime().exec("java /home/corey/git/TextEditor-java/src/classes/main/TextEditor " + file.getAbsoluteFile().toString());
            new ReadFile(file.getAbsoluteFile()).readFile();

        }
    }
}
