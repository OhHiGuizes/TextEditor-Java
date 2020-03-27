import java.io.*;
import java.util.Properties;

/**
 * Created by corey on 3/14/16.
 */

// TODO: Project Awareness
public class ReadAndWriteConfig {
	static File configFile = new File(System.getProperty("user.home") + File.separator + ".config" + File.separator + "recent-file.conf");
  static File openedFile;
  static Properties recentFiles = new Properties();

  public static File readConfig() {
  	try {
    	if (!configFile.exists()){	
			new File(System.getProperty("user.home") + File.separator + ".config").mkdirs();
			configFile.createNewFile();
      recentFiles.setProperty("file", System.getProperty("user.home") + File.separator + ".config" +File.separator  + "new.txt");
      recentFiles.store(new FileOutputStream(configFile), null);
      new File(System.getProperty("user.home") + File.separator + ".config" + File.separator + "new.txt").createNewFile();
    	}
    	recentFiles.load(new FileInputStream(configFile));
		} catch (IOException e) {
    	e.printStackTrace();
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
