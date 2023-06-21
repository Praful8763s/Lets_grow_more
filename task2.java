import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TicTacToe extends JFrame {
    private JButton[] buttons;
    private boolean isPlayerX;
    private boolean isGameOver;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[9];
        isPlayerX = true;
        isGameOver = false;

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].addActionListener(new ButtonClickListener());
            add(buttons[i]);
        }
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JButton button = (JButton) event.getSource();
            int buttonIndex = -1;

            // Find the index of the clicked button
            for (int i = 0; i < 9; i++) {
                if (buttons[i] == button) {
                    buttonIndex = i;
                    break;
                }
            }

            if (!isGameOver && button.getText().isEmpty()) {
                if (isPlayerX) {
                    button.setText("X");
                } else {
                    button.setText("O");
                }

                isPlayerX = !isPlayerX;
                checkGameOver();
            }
        }
    }

    private void checkGameOver() {
        String[] symbol = { "X", "O" };

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i * 3].getText().equals(symbol[i % 2])
                    && buttons[i * 3 + 1].getText().equals(symbol[i % 2])
                    && buttons[i * 3 + 2].getText().equals(symbol[i % 2])) {
                showWinner(symbol[i % 2]);
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(symbol[i % 2])
                    && buttons[i + 3].getText().equals(symbol[i % 2])
                    && buttons[i + 6].getText().equals(symbol[i % 2])) {
                showWinner(symbol[i % 2]);
                return;
            }
        }

        // Check diagonals
        if (buttons[0].getText().equals(symbol[0])
                && buttons[4].getText().equals(symbol[0])
                && buttons[8].getText().equals(symbol[0])) {
            showWinner(symbol[0]);
            return;
        }

        if (buttons[2].getText().equals(symbol[1])
                && buttons[4].getText().equals(symbol[1])
                && buttons[6].getText().equals(symbol[1])) {
            showWinner(symbol[1]);
            return;
        }

        // Check if it's a tie
        boolean isTie = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                isTie = false;
                break;
            }
        }

        if (isTie) {
            showWinner("Tie");
        }
    }

    private void showWinner(String winner) {
        isGameOver = true;

        if (winner.equals("Tie")) {
            JOptionPane.showMessageDialog(this, "It's a tie!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String message = String.format("Player %s wins!", winner);
            JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }

        int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Game Over",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }

        isPlayerX = true;
        isGameOver = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToe().setVisible(true);
            }
        });
    }
}
