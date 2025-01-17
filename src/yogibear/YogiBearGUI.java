package yogibear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class YogiBearGUI extends JFrame {
    private GameEngine gameArea;
    private GameStatusPanel statusPanel;
    private int lives = 3;
    private int score = 0;
    private Timer gameTimer;
    private final Font gameFont = new Font("Arial", Font.BOLD, 14);

    public YogiBearGUI() {
        initializeFrame();
        createMenuBar();
        initializeGame();
    }

    private void initializeFrame() {
        setTitle("Yogi Bear Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        statusPanel = new GameStatusPanel();
        add(statusPanel, BorderLayout.NORTH);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(232, 232, 232));

        JMenu gameMenu = new JMenu("Game");
        gameMenu.setFont(gameFont);

        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.setFont(gameFont);
        newGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newGameItem.addActionListener(e -> restartGame());

        JMenuItem highScoresItem = new JMenuItem("High Scores");
        highScoresItem.setFont(gameFont);
        highScoresItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
        highScoresItem.addActionListener(e -> showHighScores());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(gameFont);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        exitItem.addActionListener(e -> System.exit(0));

        gameMenu.add(newGameItem);
        gameMenu.add(highScoresItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
    }

    private void initializeGame() {
        gameArea = new GameEngine(this);
        add(gameArea, BorderLayout.CENTER);

        gameTimer = new Timer(16, e -> {
            gameArea.updateGame();
            updateStatus();
            gameArea.repaint();
        });

        pack();
        setLocationRelativeTo(null);
        gameTimer.start();
    }

    private void updateStatus() {
        long gameTime = (System.currentTimeMillis() - gameArea.getStartTime()) / 1000;
        statusPanel.updateStatus(
                gameArea.getCurrentLevelNumber(),
                lives,
                score,
                gameTime
        );
    }

    public void gameOver() {
        gameTimer.stop();
        String name = JOptionPane.showInputDialog(this,
                "Game Over!\nYour score: " + score + "\nEnter your name:");

        if (name != null && !name.trim().isEmpty()) {
            DatabaseManager.saveScore(name, score);
            showHighScores();
        }

        int choice = JOptionPane.showConfirmDialog(this,
                "Would you like to play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    public void restartGame() {
        lives = 3;
        score = 0;
        updateStatus();
        gameArea.resetGame();
        gameTimer.start();
        gameArea.requestFocus();
    }

    public void showHighScores() {
        JOptionPane.showMessageDialog(this,
                DatabaseManager.getTopScores(),
                "High Scores",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void addScore(int points) {
        score += points;
        updateStatus();
    }

    public void loseLife() {
        lives--;
        updateStatus();

        if (lives <= 0) {
            gameOver();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Oops! Ranger caught you!\nLives remaining: " + lives,
                    "Life Lost",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_LOST_FOCUS) {
            gameTimer.stop();
        } else if (e.getID() == WindowEvent.WINDOW_GAINED_FOCUS) {
            gameTimer.start();
            gameArea.requestFocus();
        }
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }
}