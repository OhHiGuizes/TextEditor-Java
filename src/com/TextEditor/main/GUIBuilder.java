package com.TextEditor.main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by corey on 3/11/16.
 */
public class GUIBuilder extends JFrame{
    private File originalFile;
    private List fileList;
    public GUIBuilder(File file) {
        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        originalFile = file;
        JFileChooser fc = new JFileChooser(originalFile);

        fileList = new List();
        fileList.add(originalFile.getAbsolutePath());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab(originalFile.getName(), new NewTab(originalFile));
        add(tabbedPane);
        System.out.println(tabbedPane.getTabCount());

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
            fileList.add(originalFile.getAbsolutePath());
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
                fileList.add(originalFile.getAbsolutePath());
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
            }
        });
        fileOption.add(fileOpen);

        JMenuItem fileSave = new JMenuItem("Save");
        fileSave.addActionListener(e -> {
            NewTab panel = (NewTab)tabbedPane.getSelectedComponent();
            JTextArea textArea = panel.getTextArea();
            int selector = tabbedPane.getSelectedIndex();
            File f = new File(fileList.getItem(selector));
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
        private JPanel jp;
        private JTextArea text;
        private File file;

        public NewTab(File fileName) {
            jp = new JPanel(false);
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
