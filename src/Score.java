import java.awt.*;
import javax.swing.*;

public class Score extends Rectangle {

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1score;
    int player2score;
    public boolean gameEnded = false;

    Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.BOLD, 60));

        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);

        g.drawString(String.valueOf(player1score), (GAME_WIDTH / 2) - 85, 50);
        g.drawString(String.valueOf(player2score), (GAME_WIDTH / 2) + 50, 50);
    }


    public void checkWinner() {
        if (player1score >= Panel.POINTS_TO_WIN) {
            endGame("Player 1 Wins!");
            gameEnded = true;
        }
        if (player2score >= Panel.POINTS_TO_WIN) {
            endGame("Player 2 Wins!");
            gameEnded = true;
        }
    }


    // koniec gry i okno koncowe
    private void endGame(String winnerMessage) {
        JFrame endFrame = new JFrame("Game Over");
        endFrame.setSize(400, 300);
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridLayout(4, 1));

        JLabel winnerLabel = new JLabel(winnerMessage, SwingConstants.CENTER);
        winnerLabel.setForeground(Color.WHITE);
        winnerLabel.setFont(new Font("Consolas", Font.BOLD, 24));

        JLabel scoreLabel1 = new JLabel("Final Score", SwingConstants.CENTER);
        scoreLabel1.setForeground(Color.YELLOW);
        scoreLabel1.setFont(new Font("Consolas", Font.PLAIN, 20));

        JLabel scoreLabel2 = new JLabel("Player 1   " + player1score + " : " + player2score + "   Player 2", SwingConstants.CENTER);
        scoreLabel2.setForeground(Color.YELLOW);
        scoreLabel2.setFont(new Font("Consolas", Font.PLAIN, 20));

        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setFont(new Font("Consolas", Font.BOLD, 24));

        panel.add(winnerLabel);
        panel.add(scoreLabel1);
        panel.add(scoreLabel2);
        panel.add(gameOverLabel);

        endFrame.add(panel);
        endFrame.setVisible(true);
    }
}
