import javax.swing.*;
import java.awt.*;

public class ATMRegister extends JFrame {
    private JTextField pinField, balanceField;
    private JButton registerButton;

    public ATMRegister() {
        setTitle("Create New Account");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("Set PIN:"));
        pinField = new JTextField();
        add(pinField);

        add(new JLabel("Initial Balance:"));
        balanceField = new JTextField();
        add(balanceField);

        registerButton = new JButton("Register");
        add(new JLabel()); // empty cell
        add(registerButton);

        registerButton.addActionListener(e -> {
            String pin = pinField.getText();
            String balanceText = balanceField.getText();
            try {
                double balance = Double.parseDouble(balanceText);
                if (pin.length() < 4 || balance < 0) {
                    JOptionPane.showMessageDialog(this, "Enter valid PIN (min 4 digits) and balance.");
                    return;
                }

                boolean added = ATMData.createAccount(pin, balance);
                if (added) {
                    JOptionPane.showMessageDialog(this, "Account created!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "PIN already exists.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid number.");
            }
        });

        setVisible(true);
    }
}
