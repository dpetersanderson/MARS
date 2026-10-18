   package mars.venus;

   import java.awt.Window;
   import javax.swing.JCheckBox;
   import javax.swing.JRadioButton;
   import javax.swing.JToggleButton;
   import javax.swing.SwingUtilities;
   import javax.swing.UIDefaults;
   import javax.swing.UIManager;
   import javax.swing.plaf.ColorUIResource;
   import javax.swing.plaf.metal.DefaultMetalTheme;
   import javax.swing.plaf.metal.MetalLookAndFeel;
   import javax.swing.plaf.metal.MetalTheme;

    public class DarkTheme extends DefaultMetalTheme {

      private static final ColorUIResource primary1 = new ColorUIResource(0x7a9cd0);
      private static final ColorUIResource primary2 = new ColorUIResource(0x4b6eaf);
      private static final ColorUIResource primary3 = new ColorUIResource(0x3d5f94);
      private static final ColorUIResource secondary1 = new ColorUIResource(0x5e6164);
      private static final ColorUIResource secondary2 = new ColorUIResource(0x808080);
      private static final ColorUIResource secondary3 = new ColorUIResource(0x3c3f41);
      private static final ColorUIResource black = new ColorUIResource(0xd4d4d4);
      private static final ColorUIResource white = new ColorUIResource(0x2b2b2b);

      private static String defaultLookAndFeel;
      private static MetalTheme defaultMetalTheme;

       public static void apply(boolean darkMode) {
         if (defaultLookAndFeel == null) {
            defaultLookAndFeel = UIManager.getLookAndFeel().getClass().getName();
            defaultMetalTheme = MetalLookAndFeel.getCurrentTheme();
         }
         try {
            if (darkMode) {
               MetalLookAndFeel.setCurrentTheme(new DarkTheme());
               UIManager.setLookAndFeel(new MetalLookAndFeel());
            }
            else {
               MetalLookAndFeel.setCurrentTheme(defaultMetalTheme);
               UIManager.setLookAndFeel(defaultLookAndFeel);
            }
         }
             catch (Exception e) {
               return;
            }
         resetSharedButtonUIs();
         Window[] windows = Window.getWindows();
         for (int i = 0; i < windows.length; i++) {
            SwingUtilities.updateComponentTreeUI(windows[i]);
         }
      }

       private static void resetSharedButtonUIs() {
         new JToggleButton().updateUI();
         new JRadioButton().updateUI();
         new JCheckBox().updateUI();
      }

       public String getName() {
         return "Dark";
      }

       public void addCustomEntriesToTable(UIDefaults table) {
         super.addCustomEntriesToTable(table);
         table.put("TabbedPane.background", new ColorUIResource(0x313335));
      }

       protected ColorUIResource getPrimary1() {
         return primary1;
      }

       protected ColorUIResource getPrimary2() {
         return primary2;
      }

       protected ColorUIResource getPrimary3() {
         return primary3;
      }

       protected ColorUIResource getSecondary1() {
         return secondary1;
      }

       protected ColorUIResource getSecondary2() {
         return secondary2;
      }

       protected ColorUIResource getSecondary3() {
         return secondary3;
      }

       protected ColorUIResource getBlack() {
         return black;
      }

       protected ColorUIResource getWhite() {
         return white;
      }
   }
