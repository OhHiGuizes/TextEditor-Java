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
    protected String fileName = "text.txt";
    protected String line = null;

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
	
	   setLayout(new BorderLayout());

	   textArea = new JTextArea();
	   textArea.setText(ReadFile(fileName));
	   textArea.setEditable(true);
	   textArea.setLineWrap(true);
	   textArea.setWrapStyleWord(true);
	
	   JScrollPane scrollPane = new JScrollPane(textArea);
	
	   add(scrollPane, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        add(saveButton, BorderLayout.LINE_END);
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
	   textEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   textEditor.setSize(600, 600);
       textEditor.pack();
	   textEditor.setVisible(true);
    }
}
