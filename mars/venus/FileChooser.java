package mars.venus;

import java.awt.Component;
import java.io.File;

public interface FileChooser {
  /**
   * Sets the selected file, so that it appears in the textbox/searchbar and is
   * visually
   * active in the list of files.
   */
  public void setSelectedFile(File file);

  /**
   * Gets the file that is currently selected/was selected by the user before
   * closing the chooser window.
   */
  public File getSelectedFile();

  /**
   * Sets the title for the file chooser window.
   */
  public void setDialogTitle(String title);

  /**
   * Shows the file chooser as a "save" dialog.
   * Semantically, this may cause the chooser to alert in case of an overwrite
   * (if automaticallyAsksOverwrite() is true), change the text in buttons, etc.
   */
  public int showSaveDialog(Component parent);

  /**
   * Shows the file chooser as an "open" dialog.
   */
  public int showOpenDialog(Component parent);

  /**
   * Checks whether this implementation of FileChooser automatically warns the
   * user
   * in case they attempt to overwrite an existing file.
   */
  public boolean automaticallyAsksOverwrite();

  /**
   * Returns a FileChooser implementation that works on the current environment.
   * 
   * @param dirPath The folder the FileChooser should display initially.
   */
  public static FileChooser createForCurrentPlatform(String dirPath) {
    FileChooser fc;
    if (System.getProperty("os.name").toLowerCase().contains("win")) {
      fc = new AWTFileChooser(dirPath);
    } else {
      fc = new SwingFileChooser(dirPath);
    }
    return new FileChooserDefaultExtensionDecorator(fc, "mar");
  }

  /**
   * Returns a FileChooser implementation that works on the current environment.
   * The folder the UI will display initially is implementation specific.
   */
  public static FileChooser createForCurrentPlatform() {
    return createForCurrentPlatform(null);
  }
}
