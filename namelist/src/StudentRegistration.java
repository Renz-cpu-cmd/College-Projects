import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegistration {
    private JFrame frame;
    private JTextField idField, firstNameField, lastNameField, contactField, emailField, addressField;
    private JComboBox<String> courseBox, yearBox, blockBox;
    private JRadioButton maleButton, femaleButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentRegistration window = new StudentRegistration();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StudentRegistration() {
        frame = new JFrame("Student Registration Form");
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Labels and Fields
        int row = 0;

        frame.getContentPane().add(new JLabel("Student ID:"), createGbc(0, row));
        idField = new JTextField(20);
        frame.getContentPane().add(idField, createGbc(1, row++));

        frame.getContentPane().add(new JLabel("First Name:"), createGbc(0, row));
        firstNameField = new JTextField(20);
        frame.getContentPane().add(firstNameField, createGbc(1, row++));

        frame.getContentPane().add(new JLabel("Last Name:"), createGbc(0, row));
        lastNameField = new JTextField(20);
        frame.getContentPane().add(lastNameField, createGbc(1, row++));

        frame.getContentPane().add(new JLabel("Contact Number:"), createGbc(0, row));
        contactField = new JTextField(20);
        frame.getContentPane().add(contactField, createGbc(1, row++));

        frame.getContentPane().add(new JLabel("Email Address:"), createGbc(0, row));
        emailField = new JTextField(20);
        frame.getContentPane().add(emailField, createGbc(1, row++));

        frame.getContentPane().add(new JLabel("Gender:"), createGbc(0, row));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        frame.getContentPane().add(genderPanel, createGbc(1, row++));

        frame.getContentPane().add(new JLabel("Address:"), createGbc(0, row));
        addressField = new JTextField(20);
        frame.getContentPane().add(addressField, createGbc(1, row++));

        frame.getContentPane().add(new JLabel("Course:"), createGbc(0, row));
        courseBox = new JComboBox<>(new String[]{"BSIT", "BSCS", "BSBA", "BSED"});
        frame.getContentPane().add(courseBox, createGbc(1, row++));

        frame.getContentPane().add(new JLabel("Year:"), createGbc(0, row));
        yearBox = new JComboBox<>(new String[]{"1st Year", "2nd Year", "3rd Year", "4th Year"});
        frame.getContentPane().add(yearBox, createGbc(1, row++));

        frame.getContentPane().add(new JLabel("Block:"), createGbc(0, row));
        blockBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "8"});
        frame.getContentPane().add(blockBox, createGbc(1, row++));

        // Submit Button
        JButton submitButton = new JButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = row + 1;
        gbc.gridwidth = 1;
        frame.getContentPane().add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isValidInput()) {
                    String gender = maleButton.isSelected() ? "Male" : "Female";
                    JOptionPane.showMessageDialog(frame,
                            "Registration Successful!\n\n" +
                                    "Name: " + firstNameField.getText() + " " + lastNameField.getText() + "\n" +
                                    "Student ID: " + idField.getText() + "\n" +
                                    "Contact: " + contactField.getText() + "\n" +
                                    "Email: " + emailField.getText() + "\n" +
                                    "Gender: " + gender + "\n" +
                                    "Address: " + addressField.getText() + "\n" +
                                    "Course: " + courseBox.getSelectedItem() + "\n" +
                                    "Year: " + yearBox.getSelectedItem() + "\n" +
                                    "Block: " + blockBox.getSelectedItem()
                    );
                }
            }
        });
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private boolean isValidInput() {
        if (idField.getText().isEmpty() || firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()
                || contactField.getText().isEmpty() || emailField.getText().isEmpty()
                || (!maleButton.isSelected() && !femaleButton.isSelected()) || addressField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!contactField.getText().matches("\\d{11}")) {
            JOptionPane.showMessageDialog(frame, "Contact Number must be 11 digits!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!emailField.getText().matches("^\\S+@\\S+\\.\\S+$")) {
            JOptionPane.showMessageDialog(frame, "Invalid email address!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
