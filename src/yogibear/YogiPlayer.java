package yogibear;

import java.awt.*;

public class YogiPlayer extends GameObject {
    private static final int PLAYER_SIZE = 40;
    private static final Color YOGI_COLOR = new Color(139, 69, 19); // Brown

    public YogiPlayer(int x, int y) {
        super(x, y, PLAYER_SIZE, PLAYER_SIZE, "/images/yogi.png");
    }

    public void move(int dx, int dy, int maxWidth, int maxHeight) {
        // Calculate new position
        int newX = Math.max(0, Math.min(x + dx, maxWidth - width));
        int newY = Math.max(0, Math.min(y + dy, maxHeight - height));

        // Update position
        setPosition(newX, newY);
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (image != null) {
            g2d.drawImage(image, x, y, width, height, null);
        } else {
            // Fallback drawing if image is not available
            g2d.setColor(YOGI_COLOR);
            // Body
            g2d.fillOval(x, y, width, height);
            // Eyes
            g2d.setColor(Color.BLACK);
            g2d.fillOval(x + width/4, y + height/3, 5, 5);
            g2d.fillOval(x + 3*width/4 - 5, y + height/3, 5, 5);
            // Hat
            g2d.setColor(Color.GREEN.darker());
            g2d.fillRect(x + 5, y, width - 10, 10);
        }
    }
}