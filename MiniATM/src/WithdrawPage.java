import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class WithdrawPage extends JFrame {

    private JLabel balanceLabel;
    private JTextField amountField;

    public WithdrawPage() {
        setTitle("Withdraw Funds");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header
        JLabel header = new JLabel("Withdraw Funds", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(header, gbc);

        // Current Balance
        balanceLabel = new JLabel("Current Balance: ₱" + formatAmount(ATMData.getBalance()), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 1;
        add(balanceLabel, gbc);

        // Amount Label
        JLabel label = new JLabel("Enter amount to withdraw:");
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(label, gbc);

        // Amount Field
        amountField = new JTextField();
        gbc.gridx = 1;
        add(amountField, gbc);

        // Buttons
        JButton withdrawButton = new JButton("Withdraw");
        JButton backButton = new JButton("Back to Menu");

        withdrawButton.setBackground(new Color(173, 216, 230));
        backButton.setBackground(new Color(211, 211, 211));

        withdrawButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(withdrawButton, gbc);
        gbc.gridx = 1;
        add(backButton, gbc);

        // Action for withdraw
        withdrawButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a positive amount.");
                } else if (amount > ATMData.getBalance()) {
                    JOptionPane.showMessageDialog(this, "Insufficient funds.");
                } else {
                    ATMData.withdraw(amount);
                    JOptionPane.showMessageDialog(this, "Withdrew ₱" + formatAmount(amount));
                    balanceLabel.setText("Current Balance: ₱" + formatAmount(ATMData.getBalance()));
                    amountField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number. Please enter a valid amount.");
            }
        });

        // Action for back button
        backButton.addActionListener(e -> {
            dispose();
            new ATMMainMenu(); // Return to main menu
        });

        setVisible(true);
    }

    private String formatAmount(double amount) {
        return new DecimalFormat("###,##0.00").format(amount);
    }
}
