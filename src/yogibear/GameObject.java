// GameObject.java
package yogibear;

import java.awt.*;
import javax.swing.ImageIcon;

public abstract class GameObject {
    protected int x, y;
    protected int width, height;
    protected Image image;
    protected Rectangle bounds;

    public GameObject(int x, int y, int width, int height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loadImage(imagePath);
        bounds = new Rectangle(x, y, width, height);
    }

    protected void loadImage(String imagePath) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            image = icon.getImage();
        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath);
            e.printStackTrace();
        }
    }

    public boolean collidesWith(GameObject other) {
        if (other == null) return false;
        return bounds.intersects(other.bounds);
    }

    public void updateBounds() {
        bounds.setLocation(x, y);
    }

    public abstract void draw(Graphics2D g2d);

    public int getX() { return x; }
    public int getY() { return y; }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        updateBounds();
    }
}