import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransactionHistory extends JFrame {

    private JComboBox<String> monthComboBox, sortComboBox;
    private JButton applyButton, sortButton, showAllButton;
    private JTable clientTable;
    private DefaultTableModel tableModel;

    public TransactionHistory() {
        setTitle("Transaction History");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Top Panel: Month, Apply, Sort
        JPanel topPanel = new JPanel(new FlowLayout());

        topPanel.add(new JLabel("Month:"));
        monthComboBox = new JComboBox<>(new String[]{
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        });
        topPanel.add(monthComboBox);

        applyButton = new JButton("Apply");
        topPanel.add(applyButton);

        sortComboBox = new JComboBox<>(new String[]{"Name (ASC)", "Name (DESC)", "Bill (ASC)", "Bill (DESC)"});
        topPanel.add(sortComboBox);

        sortButton = new JButton("Sort");
        topPanel.add(sortButton);

        // Table Setup
        String[] columns = {"List of Clients", "Bill"};
        tableModel = new DefaultTableModel(columns, 0);
        clientTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(clientTable);

        // Bottom Panel: Show All Clients Button
        JPanel bottomPanel = new JPanel();
        showAllButton = new JButton("Show All Clients");
        bottomPanel.add(showAllButton);

        // Sample Data
        addSampleData();

        // Layout Manager
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addSampleData() {
        tableModel.addRow(new Object[]{"Juan Dela Cruz", "₱1200"});
        tableModel.addRow(new Object[]{"Maria Santos", "₱1500"});
        tableModel.addRow(new Object[]{"Pedro Reyes", "₱900"});
        tableModel.addRow(new Object[]{"Ana Gomez", "₱1750"});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TransactionHistory().setVisible(true);
        });
    }
}
