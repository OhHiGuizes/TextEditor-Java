package com.TextEditor.main;

import javax.swing.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.io.*;

/**
 * Created by corey on 3/11/16.
 */
public class GUIBuilder extends JFrame{
    protected JTextArea textArea;
    protected File originalFile;
    protected JMenuBar menuBar;
    protected JMenu fileOption;
    protected JMenuItem fileSave, fileOpen, exit;

    public JFileChooser fc = new JFileChooser();

    public GUIBuilder(File file) {
        super();
        changeText();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        originalFile = file;

        textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(originalFile));
            textArea.read(reader, null);
            reader.close();
            textArea.requestFocus();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + originalFile + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + originalFile + "'");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);

        pane.add(scrollPane, BorderLayout.CENTER);

        menuBar = new JMenuBar();

        fileOption = new JMenu("File");
        menuBar.add(fileOption);

        fileOpen = new JMenuItem("Open");
        fileOpen.addActionListener(e -> {

            if (fc.showOpenDialog(null) == fc.APPROVE_OPTION)
            {
                File newFile = fc.getSelectedFile();
                originalFile = newFile;
                ReadAndWriteConfig.writeConfig(newFile);
                try {
                    BufferedReader buffer = new BufferedReader(new FileReader(originalFile));
                    textArea.read(buffer, null);
                    buffer.close();
                    textArea.requestFocus();
                } catch (FileNotFoundException ex) {
                    System.out.println("Unable to open file '" + originalFile + "'");
                } catch (IOException ex) {
                    System.out.println("Error reading file '" + originalFile + "'");
                }
                changeText();
            }
        });
        fileOption.add(fileOpen);

        fileSave = new JMenuItem("Save");
        fileSave.addActionListener(e -> {
            try
            {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(originalFile));
                textArea.write(bufferedWriter);
                bufferedWriter.close();

            }
            catch (FileNotFoundException ex)
            {
                System.out.println("Unable to open file '" + originalFile + "'");
            }
            catch (IOException ex)
            {
                System.out.println("Error writing file '" + originalFile + "'");
            }
        });
        fileOption.add(fileSave);

        exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        fileOption.add(exit);

        setJMenuBar(menuBar);
    }

    public void changeText() {
        setTitle(ReadAndWriteConfig.getFileName() + " - Text Editor");
    }

    public static void CreateAndShowGui(File fileName)
    {
        GUIBuilder textEditor = new GUIBuilder(fileName);
        textEditor.setSize(600, 600);
        textEditor.setVisible(true);
    }
}
