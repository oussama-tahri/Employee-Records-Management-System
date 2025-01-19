package com.employee.ui;


import com.employee.model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;

public class AddEditEmployeeDialog extends JDialog {

    private JTextField txtFullName;
    private JTextField txtJobTitle;
    private JComboBox<String> cmbDepartment;
    private JTextField txtHireDate;
    private JComboBox<String> cmbStatus;
    private JButton btnSave, btnCancel;
    private Employee employee;

    public AddEditEmployeeDialog(JFrame parent, Employee employee, boolean isEditMode) {
        super(parent, isEditMode ? "Edit Employee" : "Add Employee", true);
        this.employee = employee;
        setSize(400, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Full Name:"));
        txtFullName = new JTextField();
        formPanel.add(txtFullName);

        formPanel.add(new JLabel("Job Title:"));
        txtJobTitle = new JTextField();
        formPanel.add(txtJobTitle);

        formPanel.add(new JLabel("Department:"));
        cmbDepartment = new JComboBox<>(new String[]{"HR", "Finance", "IT", "Marketing"});
        formPanel.add(cmbDepartment);

        formPanel.add(new JLabel("Hire Date (YYYY-MM-DD):"));
        txtHireDate = new JTextField();
        formPanel.add(txtHireDate);

        formPanel.add(new JLabel("Status:"));
        cmbStatus = new JComboBox<>(new String[]{"Active", "Inactive"});
        formPanel.add(cmbStatus);

        add(formPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");

        btnSave.addActionListener(saveAction());
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        add(buttonPanel, BorderLayout.SOUTH);

        if (isEditMode && employee != null) {
            populateFields();
        }
    }

    private void populateFields() {
        txtFullName.setText(employee.getFullName());
        txtJobTitle.setText(employee.getJobTitle());
        cmbDepartment.setSelectedItem(employee.getDepartment());
        cmbStatus.setSelectedItem(employee.getEmploymentStatus());
    }

    private ActionListener saveAction() {
        return e -> {
            String fullName = txtFullName.getText().trim();
            String jobTitle = txtJobTitle.getText().trim();
            String department = (String) cmbDepartment.getSelectedItem();
            String hireDate = txtHireDate.getText().trim();
            String status = (String) cmbStatus.getSelectedItem();

            if (validateFields(fullName, jobTitle, hireDate)) {
                if (employee == null) {
                    employee = new Employee();
                }
                employee.setFullName(fullName);
                employee.setJobTitle(jobTitle);
                employee.setDepartment(department);
                employee.setEmploymentStatus(status);

                JOptionPane.showMessageDialog(this, "Employee details saved successfully.");
                dispose();
            }
        };
    }

    private boolean validateFields(String fullName, String jobTitle, String hireDate) {
        if (fullName.isEmpty() || jobTitle.isEmpty() || hireDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            java.sql.Date.valueOf(hireDate); // Validates date format
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Invalid hire date format. Use YYYY-MM-DD.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
