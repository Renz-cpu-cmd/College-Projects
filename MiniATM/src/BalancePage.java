import javax.swing.*;
import java.awt.*;

public class BalancePage extends JFrame {

    public BalancePage() {
        setTitle("Balance Inquiry");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center window
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);  // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header
        JLabel headerLabel = new JLabel("Account Balance", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(headerLabel, gbc);

        // Account Info
        String pin = ATMData.getCurrentPIN();
        JLabel accountLabel = new JLabel("Account PIN: " + pin, SwingConstants.CENTER);
        accountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 1;
        add(accountLabel, gbc);

        // Balance Info
        JLabel balanceLabel = new JLabel("Your current balance is: ₱" + String.format("%.2f", ATMData.getBalance()), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 2;
        add(balanceLabel, gbc);

        // Back Button
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(204, 204, 204));
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(140, 35));
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backButton, gbc);

        // Back button action: Return to main menu
        backButton.addActionListener(e -> {
            dispose();          // Close this window
            new ATMMainMenu();  // Open main menu
        });

        setVisible(true);
    }
}
