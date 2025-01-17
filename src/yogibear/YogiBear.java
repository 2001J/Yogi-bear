package yogibear;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class YogiBear {
    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Could not set system look and feel: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseManager.initDatabase();
                YogiBearGUI game = new YogiBearGUI();
                game.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error starting game: " + e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        });
    }
}