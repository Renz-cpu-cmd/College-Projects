import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositPage extends JFrame {

    public DepositPage() {
        setTitle("Deposit");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel header = new JLabel("Deposit Money", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        add(header, BorderLayout.NORTH);

        // Center panel for input
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel amountLabel = new JLabel("Enter amount to deposit:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(amountLabel, gbc);

        JTextField amountField = new JTextField();
        gbc.gridy = 1;
        centerPanel.add(amountField, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Footer panel for buttons
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton depositButton = new JButton("Deposit");
        JButton backButton = new JButton("Back");

        depositButton.setPreferredSize(new Dimension(120, 35));
        backButton.setPreferredSize(new Dimension(120, 35));

        footerPanel.add(depositButton);
        footerPanel.add(backButton);

        add(footerPanel, BorderLayout.SOUTH);

        // Deposit button action
        depositButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText().trim());

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a positive amount.");
                } else {
                    ATMData.deposit(amount);
                    JOptionPane.showMessageDialog(this, "Deposited ₱" + amount);
                    amountField.setText("");  // Clear input field
                    JOptionPane.showMessageDialog(this, "Updated Balance: ₱" + ATMData.getBalance());
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
            }
        });

        // Back button action: Go back to main menu
        backButton.addActionListener(e -> {
            dispose();  // Close current window
            new ATMMainMenu();  // Open main menu again
        });

        setVisible(true);
    }
}
