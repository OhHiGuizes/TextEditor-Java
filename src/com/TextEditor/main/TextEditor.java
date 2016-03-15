package com.TextEditor.main;

import java.io.File;

public class TextEditor {
    static File openingFile = ReadAndWriteConfig.readConfig();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        GUIBuilder.CreateAndShowGui(openingFile);
    }
}
