import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SimpleSearchEngine extends JFrame {
    private JTextField searchField;
    private JTextArea resultArea;

    private String[] documents = {
            "Document 1: This is the first document.",
            "Document 2: This document contains some keywords.",
            "Document 3: The third document is here.",
            "Document 4: Another document for testing."
    };

    public SimpleSearchEngine() {
        setTitle("Simple Search Engine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(searchField, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 16));
        searchButton.addActionListener(new SearchButtonListener());
        add(searchButton, BorderLayout.SOUTH);
    }

    private class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String keyword = searchField.getText().toLowerCase();
            resultArea.setText("");

            for (String document : documents) {
                if (document.toLowerCase().contains(keyword)) {
                    resultArea.append(document + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleSearchEngine().setVisible(true);
            }
        });
    }
}
