package mars.venus;

import javax.swing.Icon;
import javax.swing.KeyStroke;

import mars.tools.MipsXray;

import java.awt.event.ActionEvent;

public class XRayAction extends GuiAction {
    public XRayAction(String name, Icon icon, String descrip,
                            Integer mnemonic, KeyStroke accel, VenusUI gui) {
        super(name, icon, descrip, mnemonic, accel, gui);
    }
        
/**
 * Displays tabs with categories of information
    */	  
    public void actionPerformed(ActionEvent e) {
        MipsXray xray = new MipsXray();
        xray.action();
    }
}