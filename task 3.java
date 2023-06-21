import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ScientificCalculator extends JFrame {
    private JTextField inputField;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 20));
        add(inputField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "sin", "cos", "tan", "sqrt"
        };

        for (String buttonText : buttons) {
            JButton button = new JButton(buttonText);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            buttonPanel.add(button);
            button.addActionListener(new ButtonClickListener());
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String buttonText = ((JButton) event.getSource()).getText();
            String currentInput = inputField.getText();

            if (buttonText.equals("=")) {
                try {
                    double result = evaluateExpression(currentInput);
                    inputField.setText(String.valueOf(result));
                } catch (NumberFormatException | ArithmeticException e) {
                    inputField.setText("Error");
                }
            } else if (buttonText.equals("sqrt")) {
                try {
                    double result = Math.sqrt(Double.parseDouble(currentInput));
                    inputField.setText(String.valueOf(result));
                } catch (NumberFormatException | ArithmeticException e) {
                    inputField.setText("Error");
                }
            } else {
                inputField.setText(currentInput + buttonText);
            }
        }

        private double evaluateExpression(String expression) {

            return 0.0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScientificCalculator().setVisible(true);
            }
        });
    }
}
