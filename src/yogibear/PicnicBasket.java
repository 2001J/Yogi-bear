package yogibear;

import java.awt.*;

public class PicnicBasket extends GameObject {
    private static final int BASKET_SIZE = 30;
    private static final Color BASKET_COLOR = new Color(139, 69, 19); // Saddle Brown
    private boolean collected = false;

    public PicnicBasket(int x, int y) {
        super(x, y, BASKET_SIZE, BASKET_SIZE, "/images/basket.png");
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        collected = true;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (collected) return;

        if (image != null) {
            g2d.drawImage(image, x, y, width, height, null);
        } else {
            // Fallback drawing if image is not available
            g2d.setColor(BASKET_COLOR);
            g2d.fillRect(x + 5, y + 10, width - 10, height - 15);
            // Draw basket handle
            g2d.setColor(BASKET_COLOR.darker());
            g2d.setStroke(new BasicStroke(2));
            g2d.drawArc(x + 5, y, width - 10, height - 15, 0, 180);
        }
    }
}