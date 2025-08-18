package mars.tools;

import java.awt.Component;
import java.io.File;

import mars.venus.FileChooser;

/**
 * Adds an extension to the file chooser, so that, if the selected file
 * does not exist and has no extension, the default extension is added to it.
 */
public class FileChooserDefaultExtensionDecorator implements FileChooser {

    private FileChooser underlying;
    private String extension;

    public FileChooserDefaultExtensionDecorator(FileChooser underlying, String extension) {
        this.underlying = underlying;
        this.extension = extension;
    }

    @Override
    public void setSelectedFile(File file) {
        underlying.setSelectedFile(file);
    }

    @Override
    public File getSelectedFile() {
        File f = underlying.getSelectedFile();
        if (!f.exists() && !f.getName().contains(".")) {
            f = new File(f.getAbsolutePath() + "." + extension);
        }
        return f;
    }

    @Override
    public void setDialogTitle(String title) {
        underlying.setDialogTitle(title);
    }

    @Override
    public int showSaveDialog(Component parent) {
        return underlying.showSaveDialog(parent);
    }

    @Override
    public int showOpenDialog(Component parent) {
        return underlying.showOpenDialog(parent);
    }

    @Override
    public boolean automaticallyAsksOverwrite() {
        return underlying.automaticallyAsksOverwrite();
    }

}
