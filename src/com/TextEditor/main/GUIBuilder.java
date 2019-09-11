
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

/**
 * Created by corey on 3/11/16.
 */

// TODO: Fix scaling

public class GUIBuilder extends JFrame implements ActionListener{
    
	private File originalFile;
    private JSplitPane splitPane;
    private JMenuItem fileOpen;
    private JMenuItem fileSave;
    private JMenuItem fileNew;
    private JMenuItem exit;
    private JFileChooser fc;
    private JTabbedPane tabbedPane;
    
    public GUIBuilder(File file) {
        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        originalFile = file;
        
        fc = new JFileChooser(originalFile);

        File top = new File(System.getProperty("user.home") + "/git/TextEditor-Java/");

        tabbedPane = new JTabbedPane();
        addTab(originalFile);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
        FileTree tree = new FileTree(top);
		JScrollPane treeScroll = new JScrollPane(tree);
        splitPane.setLeftComponent(treeScroll);
		
        splitPane.setRightComponent(tabbedPane);
        add(splitPane);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileOption = new JMenu("File");

        menuBar.add(fileOption);

        fileNew = new JMenuItem("New File");
        fileNew.addActionListener(this);
        fileOption.add(fileNew);

        
        fileOpen = new JMenuItem("Open");
        fileOpen.addActionListener(this);
        fileOption.add(fileOpen);

        fileSave = new JMenuItem("Save");
        fileSave.addActionListener(this);
        fileOption.add(fileSave);

        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        fileOption.add(exit);

        setJMenuBar(menuBar);
    }

    public class TextWindow extends JComponent {
        private JTextArea text;
        private File file;

        public TextWindow(File fileName) {
            text = new JTextArea();
            file = fileName;
            text.setEditable(true);
            text.setLineWrap(false);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                text.read(reader, null);
                reader.close();
                text.requestFocus();
            } catch(FileNotFoundException ex) {
                System.out.println("Unable to open file '" + fileName + "'");
            } catch(IOException ex) {
                System.out.println("Error reading file '" + fileName + "'");
            }
            this.setLayout(new BorderLayout());
            JScrollPane scroll = new JScrollPane(text);
            add(scroll, BorderLayout.CENTER);
            
        
        }

        public File getFile() {
            return file;
        }

        public JTextArea getTextArea() {
            return text;
        }

    }

    private class NewTab extends JPanel{
    	public NewTab(String title){
    		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
    		setOpaque(false);
    		
    		JLabel label = new JLabel(title);
    		
    		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));
    		
    		add(label);
    		
    		JButton button = new CloseButton();
    		add(button);
    		
    		setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    	}
    	class CloseButton extends JButton implements ActionListener {
    		public CloseButton() {
    			setPreferredSize(new Dimension(17,17));
    			setOpaque(false);
    			setContentAreaFilled(false);
    			setBorderPainted(false);
    			setRolloverEnabled(true);
    			setFocusable(false);
    			addActionListener(this);
    		}
    		public void actionPerformed(ActionEvent e) {
    			int i = tabbedPane.indexOfTabComponent(NewTab.this);
    			if (i != -1)
    				tabbedPane.remove(i);
    		}
    		protected void paintComponent(Graphics g){
    			Graphics2D g2 = (Graphics2D)g;
    			g2.setStroke(new BasicStroke(2));
    			g2.setColor(Color.BLACK);
    			if(getModel().isRollover())
    				g2.setColor(Color.RED);
    			
    			//Paint the "X"
    			int offset = 5;
    			g2.drawLine(offset, offset, getWidth() - offset - 1, getHeight() - offset - 1);
    			g2.drawLine(getWidth() - offset - 1, offset, offset, getHeight() - offset - 1);
    			g2.dispose();
    		}
    		
    	}
    }
    
    static class FileTree extends JTree {
        public FileTree(File path){
            super(scan(path));
        }
        private static MutableTreeNode scan(File node){
            DefaultMutableTreeNode ret = new DefaultMutableTreeNode(node.getName());
            if (node.isDirectory()){
                for (File child : node.listFiles()){
                    ret.add(scan(child));
                }
            }
            return ret;
        }
    }
    
    public void addTab(File file){
    	TextWindow window = new TextWindow(file);
    	int loc = tabbedPane.getTabCount();
    	tabbedPane.insertTab(file.getName(), null, window, null, loc);
    	tabbedPane.setTabComponentAt(loc, new NewTab(file.getName()));
    }

    public static void CreateAndShowGui(File fileName)
    {
        GUIBuilder textEditor = new GUIBuilder(fileName);
        textEditor.setSize(600, 600);
        textEditor.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == fileOpen){
			if (fc.showOpenDialog(GUIBuilder.this) == fc.APPROVE_OPTION)
            {
                originalFile = fc.getSelectedFile();
                ReadAndWriteConfig.writeConfig(originalFile);
                addTab(originalFile);
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
            }
		}
		if (arg0.getSource() == fileSave){
            TextWindow panel = (TextWindow)tabbedPane.getSelectedComponent();
            JTextArea textArea = panel.getTextArea();
            File f = panel.getFile();
            System.out.println("Saving " + f.getAbsolutePath() + "...");
            try
            {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f));
                textArea.write(bufferedWriter);
                bufferedWriter.close();
            }
            catch (FileNotFoundException ex)
            {
                System.out.println("Unable to open file '" + f + "'");
            }
            catch (IOException ex)
            {
                System.out.println("Error writing file '" + f + "'");
            }
		}
		if (arg0.getSource() == fileNew){
			String name = JOptionPane.showInputDialog(this, "Name of new file: ", null);
            originalFile = new File(originalFile.getParent() + File.separator + name);
            try {
                originalFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tabbedPane.addTab(originalFile.getName(), new TextWindow(originalFile));
            tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
		}
		if (arg0.getSource() == exit){
			System.exit(0);
		}
	}
}
