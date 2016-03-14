package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
       Runtime.getRuntime().exit(0);
    }
}