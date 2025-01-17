package yogibear;

import java.awt.Color;

public class Constants {
    // Game dimensions
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int CELL_SIZE = 40;

    // Game settings
    public static final int MAX_LEVELS = 3;
    public static final int INITIAL_LIVES = 3;
    public static final int BASKET_POINTS = 100;
    public static final int FPS = 60;

    // Entity sizes
    public static final int YOGI_SIZE = 40;
    public static final int RANGER_SIZE = 40;
    public static final int BASKET_SIZE = 30;

    // Movement speeds
    public static final int RANGER_SPEED = 2;

    // Resource paths
    public static final String YOGI_IMAGE_PATH = "/images/yogi.png";
    public static final String RANGER_IMAGE_PATH = "/images/ranger.png";
    public static final String BASKET_IMAGE_PATH = "/images/basket.png";
    public static final String LEVEL_PATH_FORMAT = "/levels/level%d.txt";

    // Colors
    public static final Color BACKGROUND_COLOR = new Color(144, 238, 144);  // Forest Green
    public static final Color WALL_COLOR = new Color(128, 128, 128);      // Gray
    public static final Color YOGI_COLOR = new Color(139, 69, 19);        // Saddle Brown
    public static final Color RANGER_COLOR = new Color(0, 100, 0);        // Dark Green
    public static final Color BASKET_COLOR = new Color(205, 133, 63);     // Peru
}