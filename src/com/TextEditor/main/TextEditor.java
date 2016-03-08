import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 *
 * @author CoreyHome
 */
public class TextEditor extends JFrame implements ActionListener {

    protected JTextArea textArea;
    protected String fileName = "/home/corey/git/TextEditor-Java/src/test_files/norwalk-insert.txt";
    protected String line = null;

    protected JMenuBar menuBar;
    protected JMenu fileOption, exit;
    protected JMenuItem fileSave, fileOpen;

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
       //fileOption.addMenuListener(new thisMenuListener());
       menuBar.add(fileOption);

       fileOpen = new JMenuItem("Open");
       //fileOpen.addMenuListener(new thisMenuListener());
       fileOption.add(fileOpen);

       fileSave = new JMenuItem("Save");
       //fileSave.addMenuListener(new thisMenuListener());
       fileOption.add(fileSave);

       exit = new JMenu("Exit");
       //exit.addMenuListener(new thisMenuListener());
       menuBar.add(exit);

       setJMenuBar(menuBar);


       /* 
       JButton saveButton = new JButton("Save");
       saveButton.setActionCommand("save");
       saveButton.addActionListener(this);

       add(saveButton, BorderLayout.LINE_END);
        */
    }

    public void actionPerformed(ActionEvent e)
    {
        if ("save".equals(e.getActionCommand()))
        {
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
//       textEditor.pack();
	   textEditor.setVisible(true);
    }
}
