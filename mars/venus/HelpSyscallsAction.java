package mars.venus;

import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.KeyStroke;

public class HelpSyscallsAction extends GuiAction {

    protected HelpSyscallsAction(String name, Icon icon, String descrip, Integer mnemonic, KeyStroke accel,
            VenusUI gui) {
        super(name, icon, descrip, mnemonic, accel, gui);
    }

    public void actionPerformed(ActionEvent e) {
        HelpDialog dialog = new HelpDialog(mainUI);
        dialog.setMipsInfoActiveTab("Syscalls");
        dialog.setVisible(true);
    }
}