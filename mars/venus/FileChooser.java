package mars.venus;

import java.awt.Component;
import java.io.File;

import mars.tools.FileChooserDefaultExtensionDecorator;

public interface FileChooser {
    public void setSelectedFile(File file);
    public File getSelectedFile();
    public void setDialogTitle(String title);
    public int showSaveDialog(Component parent);
    public int showOpenDialog(Component parent);
    public boolean automaticallyAsksOverwrite();

    public static FileChooser createForCurrentPlatform(String dirPath) {
        FileChooser fc;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            fc = new AWTFileChooser(dirPath);
        } else {
            fc = new SwingFileChooser(dirPath);
        }
        return new FileChooserDefaultExtensionDecorator(fc, "mar");
    }

    public static FileChooser createForCurrentPlatform() {
        return createForCurrentPlatform(null);
    }
}
