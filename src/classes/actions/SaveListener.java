package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import file_options.WriteFile;

/**
 * Created by corey on 3/11/16.
 */
public class SaveListener implements ActionListener
{
    WriteFile content;
    public SaveListener(WriteFile fileContent)
    {
       content = fileContent;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        content.writeFile();
    }
}
