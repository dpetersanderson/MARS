package mars.venus;

import java.awt.Component;
import java.awt.FileDialog;
import java.io.File;

import javax.swing.JFileChooser;

public class AWTFileChooser extends FileDialog implements FileChooser {
    public AWTFileChooser(String dirPath) {
        super((java.awt.Frame) null);
        setDirectory(dirPath);
    }

    public AWTFileChooser() {
        super((java.awt.Frame) null);
    }

    @Override
    public File getSelectedFile() {
        return new File(getFile()).getAbsoluteFile();
    }

    @Override
    public void setSelectedFile(File file) {
        setFile(file.getAbsolutePath());
    }

    @Override
    public void setDialogTitle(String title) {
        setTitle(title);
    }

    @Override
    public int showSaveDialog(Component unused) {
        return showDialogWithMode(FileDialog.SAVE);
    }

    @Override
    public int showOpenDialog(Component unused) {
        return showDialogWithMode(FileDialog.LOAD);
    }

    private int showDialogWithMode(int mode) {
        setMode(mode);
        setMultipleMode(false);
        setVisible(true);
        String choice = getFile();
        if (choice != null) {
            return JFileChooser.APPROVE_OPTION;
        }

        return JFileChooser.CANCEL_OPTION;
    }

    @Override
    public boolean automaticallyAsksOverwrite() {
        return true;
    }
}
