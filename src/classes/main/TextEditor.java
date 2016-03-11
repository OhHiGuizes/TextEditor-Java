package main;

public class TextEditor {
    protected static String fileName = "/home/corey/git/TextEditor-Java/src/test_files/text.txt";

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        TextEditor.fileName = fileName;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       GUIBuilder textEditor = new GUIBuilder(fileName);
       textEditor.setSize(600, 600);
	   textEditor.setVisible(true);
    }
}
