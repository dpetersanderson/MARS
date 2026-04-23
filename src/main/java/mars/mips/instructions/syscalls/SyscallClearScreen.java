package mars.mips.instructions.syscalls;

import mars.*;
import mars.mips.instructions.syscalls.AbstractSyscall;
import mars.ProcessingException;
import mars.ProgramStatement;
import javax.swing.*;

/**
 * Syscall 60: Clear the Run I/O console window in MARS (GUI only).
 *
 * Usage in MIPS:
 *   li $v0, 60
 *   syscall
 */
public class SyscallClearScreen extends AbstractSyscall {

    public SyscallClearScreen() {
        super(60, "ClearScreen");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        // Only works when GUI is active
        if (Globals.getGui() == null) return;

        JTextArea runConsole = Globals.getGui()
                .getMessagesPane()
                .getRunTextArea();

        if (runConsole != null) {
            SwingUtilities.invokeLater(() -> runConsole.setText(""));
        }
    }
}
