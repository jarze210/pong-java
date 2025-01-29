import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new MenuFrame();
    }
}


class MenuFrame extends JFrame {

    public MenuFrame() {
        setTitle("Select Game Mode");
        setSize(Panel.GAME_WIDTH, Panel.GAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.BLACK);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.gridx = 0;

        JLabel titleLabel = new JLabel("Select Game Mode");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 32));
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        JButton singlePlayerButton = createStyledButton("Single Player");
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameFrame frame = new GameFrame(true);
            }
        });
        gbc.gridy = 1;
        panel.add(singlePlayerButton, gbc);

        JButton multiPlayerButton = createStyledButton("Multiplayer");
        multiPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameFrame frame = new GameFrame(false);
            }
        });
        gbc.gridy = 2;
        panel.add(multiPlayerButton, gbc);

        add(panel);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Consolas", Font.PLAIN, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.DARK_GRAY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.DARK_GRAY);
            }
        });

        return button;
    }
}
