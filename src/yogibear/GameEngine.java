package yogibear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameEngine extends JPanel {
    private YogiBearGUI gameFrame;
    private YogiPlayer yogi;
    private Level currentLevel;
    private boolean gameRunning = true;
    private long startTime;
    private int currentLevelNumber = 1;
    private static final int MAX_LEVELS = 3;

    public GameEngine(YogiBearGUI frame) {
        this.gameFrame = frame;
        setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        setFocusable(true);
        initializeGame();
        setupKeyBindings();
    }

    private void initializeGame() {
        yogi = new YogiPlayer(0, 0);
        currentLevel = new Level(currentLevelNumber);
        startTime = System.currentTimeMillis();
    }

    private void setupKeyBindings() {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke("W"), "moveUp");
        im.put(KeyStroke.getKeyStroke("S"), "moveDown");
        im.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        im.put(KeyStroke.getKeyStroke("D"), "moveRight");

        am.put("moveUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (gameRunning) yogi.move(0, -Constants.CELL_SIZE, getWidth(), getHeight());
            }
        });

        am.put("moveDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (gameRunning) yogi.move(0, Constants.CELL_SIZE, getWidth(), getHeight());
            }
        });

        am.put("moveLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (gameRunning) yogi.move(-Constants.CELL_SIZE, 0, getWidth(), getHeight());
            }
        });

        am.put("moveRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (gameRunning) yogi.move(Constants.CELL_SIZE, 0, getWidth(), getHeight());
            }
        });
    }

    public void updateGame() {
        if (!gameRunning) return;

        for (Ranger ranger : currentLevel.getRangers()) {
            ranger.move(getWidth(), getHeight());
            if (ranger.collidesWith(yogi)) {
                gameFrame.loseLife();
                resetYogi();
            }
        }

        for (PicnicBasket basket : currentLevel.getBaskets()) {
            if (!basket.isCollected() && basket.collidesWith(yogi)) {
                basket.collect();
                gameFrame.addScore(Constants.BASKET_POINTS);
            }
        }

        if (currentLevel.isCompleted()) {
            loadNextLevel();
        }
    }

    private void resetYogi() {
        yogi.setPosition(0, 0);
    }

    private void loadNextLevel() {
        currentLevelNumber++;
        if (currentLevelNumber <= MAX_LEVELS) {
            currentLevel = new Level(currentLevelNumber);
            resetYogi();
            startTime = System.currentTimeMillis();
        } else {
            gameRunning = false;
            JOptionPane.showMessageDialog(this,
                    "Congratulations! You've completed all levels!",
                    "Game Completed",
                    JOptionPane.INFORMATION_MESSAGE);
            gameFrame.gameOver();
        }
    }

    public void resetGame() {
        gameRunning = true;
        currentLevelNumber = 1;
        initializeGame();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw background
        g2d.setColor(Constants.BACKGROUND_COLOR);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw obstacles
        g2d.setColor(Constants.WALL_COLOR);
        for (Rectangle obstacle : currentLevel.getObstacles()) {
            g2d.fill(obstacle);
        }

        // Draw game objects
        for (PicnicBasket basket : currentLevel.getBaskets()) {
            basket.draw(g2d);
        }

        for (Ranger ranger : currentLevel.getRangers()) {
            ranger.draw(g2d);
        }

        yogi.draw(g2d);
    }

    public long getStartTime() {
        return startTime;
    }

    public int getCurrentLevelNumber() {
        return currentLevelNumber;
    }
}