package com.employee.ui;

import javax.swing.*;
import java.awt.*;

public class EditEmployeeDialog extends JDialog {

    private JTextField txtName;
    private JTextField txtJobTitle;
    private JTextField txtDepartment;
    private JButton btnSave, btnCancel;

    public EditEmployeeDialog(JFrame parent, String currentName, String currentJobTitle, String currentDepartment) {
        super(parent, "Edit Employee Details", true);
        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Full Name:"));
        txtName = new JTextField(currentName);
        formPanel.add(txtName);

        formPanel.add(new JLabel("Job Title:"));
        txtJobTitle = new JTextField(currentJobTitle);
        formPanel.add(txtJobTitle);

        formPanel.add(new JLabel("Department:"));
        txtDepartment = new JTextField(currentDepartment);
        formPanel.add(txtDepartment);

        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");

        btnSave.addActionListener(e -> saveChanges());
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveChanges() {
        String name = txtName.getText();
        String jobTitle = txtJobTitle.getText();
        String department = txtDepartment.getText();

        if (name.isEmpty() || jobTitle.isEmpty() || department.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Logic to update the employee (could involve backend integration)
            JOptionPane.showMessageDialog(this, "Employee details updated successfully!");
            dispose();
        }
    }
}