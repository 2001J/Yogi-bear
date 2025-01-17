package yogibear;

import javax.swing.*;
import java.awt.*;

public class GameStatusPanel extends JPanel {
    private final Font statusFont = new Font("Arial", Font.BOLD, 14);
    private final JLabel levelLabel;
    private final JLabel livesLabel;
    private final JLabel scoreLabel;
    private final JLabel timeLabel;

    public GameStatusPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        setBackground(new Color(51, 51, 51));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        levelLabel = createLabel("Level: 1");
        livesLabel = createLabel("Lives: 3");
        scoreLabel = createLabel("Score: 0");
        timeLabel = createLabel("Time: 0");

        add(levelLabel);
        add(livesLabel);
        add(scoreLabel);
        add(timeLabel);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(statusFont);
        label.setForeground(Color.WHITE);
        return label;
    }

    public void updateStatus(int level, int lives, int score, long time) {
        levelLabel.setText(String.format("Level: %d", level));
        livesLabel.setText(String.format("Lives: %d", lives));
        scoreLabel.setText(String.format("Score: %d", score));
        timeLabel.setText(String.format("Time: %ds", time));
    }
}