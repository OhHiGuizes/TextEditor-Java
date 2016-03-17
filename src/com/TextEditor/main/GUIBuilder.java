package com.TextEditor.main;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by corey on 3/11/16.
 */
public class GUIBuilder extends JFrame{
    private File originalFile;
    private JSplitPane splitPane;
    public GUIBuilder(File file) {
        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        originalFile = file;
        JFileChooser fc = new JFileChooser(originalFile);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab(originalFile.getName(), new NewTab(originalFile));
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(new JLabel("<html><p>Tree View Coming Soon...</p></html>"));
        splitPane.setRightComponent(tabbedPane);
        add(splitPane);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileOption = new JMenu("File");

        menuBar.add(fileOption);

        JMenuItem fileNew = new JMenuItem("New File");
        fileNew.addActionListener(e1 -> {
            String name = JOptionPane.showInputDialog(this, "Name of new file: ", null);
            originalFile = new File(originalFile.getParent() + File.separator + name);
            try {
                originalFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tabbedPane.addTab(originalFile.getName(), new NewTab(originalFile));
            tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
        });
        fileOption.add(fileNew);

        JMenuItem fileOpen = new JMenuItem("Open");
        fileOpen.addActionListener(e -> {
            if (fc.showOpenDialog(GUIBuilder.this) == fc.APPROVE_OPTION)
            {
                originalFile = fc.getSelectedFile();
                ReadAndWriteConfig.writeConfig(originalFile);
                tabbedPane.addTab(originalFile.getName(), new NewTab(originalFile));
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
            }
        });
        fileOption.add(fileOpen);

        JMenuItem fileSave = new JMenuItem("Save");
        fileSave.addActionListener(e -> {
            NewTab panel = (NewTab)tabbedPane.getSelectedComponent();
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
        });
        fileOption.add(fileSave);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        fileOption.add(exit);

        setJMenuBar(menuBar);
    }
    class NewTab extends JComponent {
        private JTextArea text;
        private File file;

        public NewTab(File fileName) {
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
    public static void CreateAndShowGui(File fileName)
    {
        GUIBuilder textEditor = new GUIBuilder(fileName);
        textEditor.setSize(600, 600);
        textEditor.setVisible(true);
    }
}
