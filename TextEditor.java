import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 *
 * @author CoreyHome
 */
public class TextEditor extends JPanel {

    protected JTextArea textArea;
    protected String fileName;
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

    public TextEditor()
    {
	setLayout(new BorderLayout());

	textArea = new JTextArea(5, 20);
	textArea.setText(ReadFile("test.txt"));
	textArea.setEditable(true);
	JScrollPane scrollPane = new JScrollPane(textArea);

	add(scrollPane, BorderLayout.CENTER);
    }

    public static void createGUI()
    {
	JFrame frame = new JFrame("Text Editor");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(new TextEditor());
	frame.pack();
	frame.setVisible(true);
	}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
	{
            public void run()
            {
		createGUI();
            }
	});
    }
}
