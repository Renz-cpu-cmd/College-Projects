import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMlogin extends JFrame {
    private JPasswordField pinField;
    private JButton loginButton, registerButton;
    private JLabel statusLabel;
    private JCheckBox showPinCheckbox;

    public ATMlogin() {
        setTitle("ATM Login");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel headerLabel = new JLabel("Welcome to ATM", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(headerLabel, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        centerPanel.add(new JLabel("Enter PIN:"));
        pinField = new JPasswordField();
        centerPanel.add(pinField);

        showPinCheckbox = new JCheckBox("Show PIN");
        showPinCheckbox.addActionListener(e -> togglePinVisibility());
        centerPanel.add(showPinCheckbox);

        loginButton = new JButton("Login");
        centerPanel.add(loginButton);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom section with status + register
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        bottomPanel.add(statusLabel);

        registerButton = new JButton("Create New Account");
        bottomPanel.add(registerButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Listeners
        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> openRegistration());
        pinField.addActionListener(e -> handleLogin());

        setVisible(true);
    }

    private void handleLogin() {
        String pin = new String(pinField.getPassword()); // Use getPassword() for security
        if (ATMData.validatePIN(pin)) {
            statusLabel.setText("Login successful!");
            dispose(); // Close the login window
            new ATMMainMenu(); // Open the main menu window
        } else {
            statusLabel.setText("Invalid PIN. Try again.");
            pinField.setText(""); // Clear the PIN field
        }
    }

    private void togglePinVisibility() {
        pinField.setEchoChar(showPinCheckbox.isSelected() ? (char) 0 : '•');
    }

    private void openRegistration() {
        new ATMRegister(); // Open the registration screen
    }

    public static void main(String[] args) {
        new ATMlogin(); // Show the login screen
    }
}
