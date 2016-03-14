package main;

import java.io.File;

public class TextEditor {
    static File fileName = new File("/home/corey/git/TextEditor-Java/src/test_files/text.txt");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        GUIBuilder.CreateAndShowGui(fileName);
    }
}
