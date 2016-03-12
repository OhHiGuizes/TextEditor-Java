package main;

import actions.*;
import file_options.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by corey on 3/11/16.
 */
public class GUIBuilder extends JFrame{
    public static JTextArea textArea;

    protected JMenuBar menuBar;
    protected JMenu fileOption;
    protected JMenuItem fileSave, fileOpen, exit;

    public static JFileChooser fc = new JFileChooser();

    public GUIBuilder(File file)
    {
        super(file.getName() + " - Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        ReadFile fileContent = new ReadFile(file);
        WriteFile newFileContent = new WriteFile(file);

        textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);

        pane.add(scrollPane, BorderLayout.CENTER);

        menuBar = new JMenuBar();

        fileOption = new JMenu("File");
        menuBar.add(fileOption);

        fileOpen = new JMenuItem("Open");
        fileOpen.addActionListener(new OpenListener());
        fileOption.add(fileOpen);

        fileSave = new JMenuItem("Save");
        fileSave.addActionListener(new SaveListener(file));
        fileOption.add(fileSave);

        exit = new JMenuItem("Exit");
        exit.addActionListener(new CloseListener());
        fileOption.add(exit);

        setJMenuBar(menuBar);
        fileContent.readFile();
    }

    public static String getText()
    {
        return textArea.getText();
    }

    public static void CreateAndShowGui(File fileName)
    {
        GUIBuilder textEditor = new GUIBuilder(fileName);
        textEditor.setSize(600, 600);
        textEditor.setVisible(true);
    }
}
