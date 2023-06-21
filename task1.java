import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

class CurrencyConverter extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;

    private double exchangeRate = 0.85; // Example exchange rate: 1 USD = 0.85 EUR

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 20));
        add(inputField);

        JButton convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.PLAIN, 20));
        convertButton.addActionListener(new ConvertButtonListener());
        add(convertButton);

        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultLabel);
    }

    private class ConvertButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String inputText = inputField.getText();

            try {
                double amount = Double.parseDouble(inputText);
                double convertedAmount = amount * exchangeRate;
                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                String resultText = decimalFormat.format(convertedAmount) + " EUR";
                resultLabel.setText(resultText);
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid input");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverter().setVisible(true);
            }
        });
    }
}
