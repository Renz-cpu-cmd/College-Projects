import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ElectricBillCalculator extends JFrame {
    private JComboBox<String> monthComboBox;
    private JTextField lastNameField, firstNameField, middleNameField;
    private JSpinner kWhSpinner, rateSpinner;
    private JLabel totalLabel;

    public ElectricBillCalculator() {
        setTitle("Electric Bill Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 5, 5));

        // Month
        add(new JLabel("Month:"));
        monthComboBox = new JComboBox<>(new String[]{
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        });
        add(monthComboBox);

        // Names
        add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Middle Name (Optional):"));
        middleNameField = new JTextField();
        add(middleNameField);

        // Spinners for number input
        add(new JLabel("Consumed electricity in a month (in kWh):"));
        kWhSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 1));
        add(kWhSpinner);

        add(new JLabel("Enter the rate per kWh (in PHP):"));
        rateSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        add(rateSpinner);

        // Total display
        add(new JLabel("Total:"));
        totalLabel = new JLabel("₱0.00");
        add(totalLabel);

        // Buttons
        JButton historyButton = new JButton("Transaction History");
        JButton saveButton = new JButton("Save");
        JButton exitButton = new JButton("Exit");

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(historyButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);
        add(buttonPanel);
        add(new JLabel()); // Placeholder

        // Action listeners
        saveButton.addActionListener(e -> calculateBill());
        exitButton.addActionListener(e -> System.exit(0));
        historyButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Feature under development."));

        setVisible(true);
    }

    private void calculateBill() {
        int kWh = (int) kWhSpinner.getValue();
        int rate = (int) rateSpinner.getValue();
        int total = kWh * rate;
        totalLabel.setText(String.format("₱%.2f", (double) total));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ElectricBillCalculator::new);
    }
}