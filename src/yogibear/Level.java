package yogibear;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Level {
    private final List<PicnicBasket> baskets;
    private final List<Ranger> rangers;
    private final List<Rectangle> obstacles;
    protected final int levelNumber;
    private static final int CELL_SIZE = 40;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        baskets = new ArrayList<>();
        rangers = new ArrayList<>();
        obstacles = new ArrayList<>();
        loadLevel();
    }

    private void loadLevel() {
        String filename = String.format("/levels/level%d.txt", levelNumber);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(filename))))) {

            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                processLine(line, y);
                y++;
            }
        } catch (IOException | NullPointerException e) {
            System.err.println("Error loading level " + levelNumber + ": " + e.getMessage());
            // Create a basic level if file loading fails
            createDefaultLevel();
        }
    }

    private void processLine(String line, int y) {
        for (int x = 0; x < line.length(); x++) {
            int pixelX = x * CELL_SIZE;
            int pixelY = y * CELL_SIZE;

            switch (line.charAt(x)) {
                case 'B' -> baskets.add(new PicnicBasket(pixelX, pixelY));
                case 'R' -> rangers.add(new Ranger(pixelX, pixelY, true));
                case 'V' -> rangers.add(new Ranger(pixelX, pixelY, false));
                case '#' -> obstacles.add(new Rectangle(pixelX, pixelY, CELL_SIZE, CELL_SIZE));
            }
        }
    }

    private void createDefaultLevel() {
        // Add some default obstacles
        obstacles.add(new Rectangle(200, 200, CELL_SIZE, CELL_SIZE));
        obstacles.add(new Rectangle(400, 400, CELL_SIZE, CELL_SIZE));

        // Add default baskets
        baskets.add(new PicnicBasket(100, 100));
        baskets.add(new PicnicBasket(500, 300));

        // Add default rangers
        rangers.add(new Ranger(300, 200, true));
        rangers.add(new Ranger(200, 300, false));
    }

    public void draw(Graphics2D g2d) {
        // Draw obstacles
        g2d.setColor(Color.GRAY);
        for (Rectangle obstacle : obstacles) {
            g2d.fill(obstacle);
        }

        // Draw baskets
        for (PicnicBasket basket : baskets) {
            basket.draw(g2d);
        }

        // Draw rangers
        for (Ranger ranger : rangers) {
            ranger.draw(g2d);
        }
    }

    public List<PicnicBasket> getBaskets() { return baskets; }
    public List<Ranger> getRangers() { return rangers; }
    public List<Rectangle> getObstacles() { return obstacles; }
    public boolean isCompleted() {
        return baskets.stream().allMatch(PicnicBasket::isCollected);
    }
}