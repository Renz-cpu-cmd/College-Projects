import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMMainMenu extends JFrame {

    public ATMMainMenu() {
        setTitle("ATM Main Menu");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Header
        JLabel header = new JLabel("Select a Transaction", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        header.setForeground(Color.BLACK);  // Set text color

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        // Buttons
        JButton checkBalanceButton = createStyledButton("Check Balance");
        JButton depositButton = createStyledButton("Deposit");
        JButton withdrawButton = createStyledButton("Withdraw");
        JButton viewHistoryButton = createStyledButton("View Transaction History");
        JButton exitButton = createStyledButton("Exit");

        // Add buttons to the panel
        buttonPanel.add(checkBalanceButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(exitButton);

        // Button actions to open new windows
        checkBalanceButton.addActionListener(e -> {
            new BalancePage();
            dispose();  // Close the main menu after transitioning
        });

        depositButton.addActionListener(e -> {
            new DepositPage();
            dispose();
        });

        withdrawButton.addActionListener(e -> {
            new WithdrawPage();
            dispose();
        });

        viewHistoryButton.addActionListener(e -> {
            new TransactionHistoryPage();
            dispose();
        });

        exitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Thank you for using the ATM!");
                dispose();
                new ATMlogin();  // Return to login screen
            }
        });

        // Set background image
        setBackgroundImage(header, buttonPanel);

        setVisible(true);
    }

    // Method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(0, 153, 255)); // Blue color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 50));  // Set button size
        button.setToolTipText("Click to " + text.toLowerCase());  // Tooltip text
        return button;
    }

    // Method to set a background image
    private void setBackgroundImage(JLabel header, JPanel buttonPanel) {
        ImageIcon backgroundImage = new ImageIcon("path/to/your/image.jpg");  // Update with your image path
        Image img = backgroundImage.getImage();
        Image scaledImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);  // Scale the image to fit the window
        backgroundImage = new ImageIcon(scaledImg);  // Create a new ImageIcon from the scaled image

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);  // Set the content pane to the background label

        // Create a transparent panel for your components
        JPanel panel = new JPanel();
        panel.setOpaque(false);  // Make sure the panel is transparent
        panel.setLayout(new BorderLayout());  // Use BorderLayout to add components properly
        panel.add(header, BorderLayout.NORTH);  // Add the header at the top
        panel.add(buttonPanel, BorderLayout.CENTER);  // Add the button panel in the center

        // Add the components to the background label
        backgroundLabel.add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMMainMenu()); // Initialize the ATM Main Menu
    }
}
