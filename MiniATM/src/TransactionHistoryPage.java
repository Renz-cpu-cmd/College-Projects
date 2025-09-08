import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TransactionHistoryPage extends JFrame {

    public TransactionHistoryPage() {
        setTitle("Transaction History");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel header = new JLabel("Transaction History", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(header, BorderLayout.NORTH);

        // Get transaction history from ATMData
        List<String> history = ATMData.getTransactionHistory();

        // Build transaction history content
        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        historyPanel.setBackground(Color.WHITE);

        if (history.isEmpty()) {
            JLabel noHistoryLabel = new JLabel("No transactions available.");
            noHistoryLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            noHistoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            historyPanel.add(noHistoryLabel);
        } else {
            for (String transaction : history) {
                JLabel transactionLabel = new JLabel(transaction);
                transactionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                transactionLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
                historyPanel.add(transactionLabel);
            }
        }

        // Add scroll pane in case of long transaction history
        JScrollPane scrollPane = new JScrollPane(historyPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(211, 211, 211));
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.addActionListener(e -> {
            dispose();
            new ATMMainMenu();
        });

        JPanel footerPanel = new JPanel();
        footerPanel.add(backButton);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TransactionHistoryPage::new);
    }
}
