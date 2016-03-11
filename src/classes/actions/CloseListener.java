// Goal here is to make all the Listeners seperate in these folders, to remove bulk from the main class.
// But of course I suck.
// package actions;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}