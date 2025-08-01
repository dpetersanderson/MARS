package mars.venus;

import javax.swing.JFileChooser;

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
