package yogibear;

import java.awt.*;

public class Ranger extends GameObject {
    private boolean movingHorizontally;
    private int direction = 1;
    private static final int RANGER_SIZE = 40;
    private static final int SPEED = 2;
    private static final Color RANGER_COLOR = new Color(34, 139, 34); // Forest Green

    public Ranger(int x, int y, boolean movingHorizontally) {
        super(x, y, RANGER_SIZE, RANGER_SIZE, "/images/ranger.png");
        this.movingHorizontally = movingHorizontally;
    }

    public void move(int maxWidth, int maxHeight) {
        // Check for collisions with boundaries
        if (movingHorizontally) {
            int nextX = x + (direction * SPEED);
            if (nextX <= 0 || nextX >= maxWidth - width) {
                direction *= -1;
            }
            x += direction * SPEED;
        } else {
            int nextY = y + (direction * SPEED);
            if (nextY <= 0 || nextY >= maxHeight - height) {
                direction *= -1;
            }
            y += direction * SPEED;
        }
        updateBounds();
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (image != null) {
            g2d.drawImage(image, x, y, width, height, null);
        } else {
            // Fallback drawing if image is not available
            g2d.setColor(RANGER_COLOR);
            g2d.fillRect(x, y, width, height);
            // Add visual indication of movement direction
            g2d.setColor(Color.WHITE);
            int arrowSize = 10;
            if (movingHorizontally) {
                if (direction > 0) {
                    g2d.fillPolygon(
                            new int[]{x + width - 5, x + width - 5, x + width - 5 + arrowSize},
                            new int[]{y + height/2 - arrowSize/2, y + height/2 + arrowSize/2, y + height/2},
                            3
                    );
                } else {
                    g2d.fillPolygon(
                            new int[]{x + 5, x + 5, x + 5 - arrowSize},
                            new int[]{y + height/2 - arrowSize/2, y + height/2 + arrowSize/2, y + height/2},
                            3
                    );
                }
            } else {
                if (direction > 0) {
                    g2d.fillPolygon(
                            new int[]{x + width/2 - arrowSize/2, x + width/2 + arrowSize/2, x + width/2},
                            new int[]{y + height - 5, y + height - 5, y + height - 5 + arrowSize},
                            3
                    );
                } else {
                    g2d.fillPolygon(
                            new int[]{x + width/2 - arrowSize/2, x + width/2 + arrowSize/2, x + width/2},
                            new int[]{y + 5, y + 5, y + 5 - arrowSize},
                            3
                    );
                }
            }
        }
    }
}