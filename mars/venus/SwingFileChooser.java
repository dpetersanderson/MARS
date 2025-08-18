package mars.venus;

import javax.swing.JFileChooser;

/**
 * FileChooser that uses javax.swing.JFileChooser in its implementation.
 */
public class SwingFileChooser extends JFileChooser implements FileChooser {
    public SwingFileChooser(String dirPath) {
        super(dirPath);
    }

    public SwingFileChooser() {
        super();
    }

    @Override
    public boolean automaticallyAsksOverwrite() {
        return false;
    }
}
