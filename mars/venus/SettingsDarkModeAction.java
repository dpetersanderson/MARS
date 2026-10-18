   package mars.venus;

   import mars.*;
   import java.awt.event.*;
   import javax.swing.*;

    public class SettingsDarkModeAction extends GuiAction {

       public SettingsDarkModeAction(String name, Icon icon, String descrip,
                             Integer mnemonic, KeyStroke accel, VenusUI gui) {
         super(name, icon, descrip, mnemonic, accel, gui);
      }

       public void actionPerformed(ActionEvent e) {
         boolean darkMode = ((JCheckBoxMenuItem) e.getSource()).isSelected();
         Globals.getSettings().setBooleanSetting(Settings.DARK_MODE, darkMode);
         DarkTheme.apply(darkMode);
      }
   }
