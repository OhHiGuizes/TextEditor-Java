package actions;

import main.GUIBuilder;
import main.TextEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

            GUIBuilder textEditor = new GUIBuilder(file.getAbsoluteFile().toString());
            textEditor.setSize(600, 600);
            textEditor.setVisible(true);
        }
    }
}
