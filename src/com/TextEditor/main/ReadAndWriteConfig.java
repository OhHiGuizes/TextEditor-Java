package com.TextEditor.main;
// "/home/corey/git/TextEditor-Java/src/com/TextEditor/test_files/text.txt"
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.Properties;

/**
 * Created by corey on 3/14/16.
 */
public class ReadAndWriteConfig {
    static File configFile = new File(System.getProperty("user.home") + "/git/TextEditor-Java/src/com/TextEditor/.config/recent-file.conf");
    static File openedFile;
    static Properties recentFiles = new Properties();

    public static File readConfig() {
        try {
            recentFiles.load(new FileInputStream(configFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (recentFiles.getProperty("file") == null) {
            openedFile = new File(System.getProperty("user.home") + "/git/TextEditor-Java/src/com/TextEditor/test_files/text.txt");
            recentFiles.setProperty("file", openedFile.getAbsolutePath());
        }

        return new File(recentFiles.getProperty("file"));
    }

    public static String getFileName() {
        File tempFile = new File (recentFiles.getProperty("file"));
        return tempFile.getName();
    }

    public static void writeConfig(File file) {
        openedFile = file;
        recentFiles.setProperty("file", openedFile.getAbsolutePath());
        try {
            recentFiles.store(new FileOutputStream(configFile), null);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
