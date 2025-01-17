package yogibear;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASS = System.getenv("DB_PASS");

    public static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = """
                CREATE TABLE IF NOT EXISTS high_scores (
                    id SERIAL PRIMARY KEY,
                    player_name VARCHAR(50) NOT NULL,
                    score INTEGER NOT NULL,
                    played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )""";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println("Database Connection Error: " + e.getMessage());
        }
    }

    public static void saveScore(String name, int score) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO high_scores (player_name, score) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, score);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error saving score: " + e.getMessage());
        }
    }

    public static String getTopScores() {
        StringBuilder scores = new StringBuilder();
        scores.append("Top 10 High Scores\n");
        scores.append("------------------\n\n");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT player_name, score, played_at FROM high_scores ORDER BY score DESC LIMIT 10";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    scores.append(String.format("%-15s %6d pts  %s%n",
                            rs.getString("player_name"),
                            rs.getInt("score"),
                            rs.getTimestamp("played_at").toString().substring(0, 16)
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting high scores: " + e.getMessage());
            return "Error loading high scores.";
        }

        return scores.toString();
    }

    public static void clearScores() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM high_scores";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println("Error clearing scores: " + e.getMessage());
        }
    }
}