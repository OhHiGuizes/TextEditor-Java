import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// import classes.CloseListener;

/**
 *
 * @author CoreyHome
 */
public class TextEditor extends JFrame {

    protected JTextArea textArea;
    protected String fileName = "/home/corey/git/TextEditor-Java/src/test_files/text.txt";
    protected String line = null;

    protected JMenuBar menuBar;
    protected JMenu fileOption;
    protected JMenuItem fileSave, fileOpen, exit;

    public String ReadFile(String file)
    {
        fileName = file;
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

    public void writeFile(String file)
    {
        fileName = file;

        try
        {
            PrintWriter printMe = new PrintWriter(fileName);
            printMe.print(textArea.getText());
            printMe.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Unable to open file '" + fileName + "'");
        }
    }

    public TextEditor()
    {
	   super("Text Editor");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Container pane = getContentPane();
	   pane.setLayout(new BorderLayout());

	   textArea = new JTextArea();

	   textArea.setText(ReadFile(fileName));
	   textArea.setEditable(true);
	   textArea.setLineWrap(true);
	   textArea.setWrapStyleWord(true);
	
	   JScrollPane scrollPane = new JScrollPane(textArea);
	
	   pane.add(scrollPane, BorderLayout.CENTER);

       menuBar = new JMenuBar();

       fileOption = new JMenu("File");
       menuBar.add(fileOption);

       fileOpen = new JMenuItem("Open");
       //fileOpen.addActionListener(this);
       fileOption.add(fileOpen);

       fileSave = new JMenuItem("Save");
       fileSave.addActionListener(new SaveListener());
       fileOption.add(fileSave);

       exit = new JMenuItem("Exit");
       exit.addActionListener(new CloseListener());
       fileOption.add(exit);

       setJMenuBar(menuBar);
    }


    private class CloseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            writeFile(fileName);
        }
    }

   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       TextEditor textEditor = new TextEditor();
       textEditor.setSize(600, 600);
	   textEditor.setVisible(true);
    }
}
