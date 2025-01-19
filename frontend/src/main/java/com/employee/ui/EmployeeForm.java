package com.employee.ui;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class EmployeeForm extends JFrame {
    private final EmployeeService employeeService;
    private final Employee employee;

    private final JTextField fullNameField;
    private final JTextField jobTitleField;
    private final JTextField departmentField;
    private final JTextField statusField;
    private final JTextField contactField;
    private final JTextField addressField;

    public EmployeeForm(EmployeeService employeeService, Employee employee, EmployeePanel parentPanel) {
        this.employeeService = employeeService;
        this.employee = employee;

        setTitle(employee == null ? "Add Employee" : "Edit Employee");
        setSize(400, 300);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Full Name:"));
        fullNameField = new JTextField();
        add(fullNameField);

        add(new JLabel("Job Title:"));
        jobTitleField = new JTextField();
        add(jobTitleField);

        add(new JLabel("Department:"));
        departmentField = new JTextField();
        add(departmentField);

        add(new JLabel("Employment Status:"));
        statusField = new JTextField();
        add(statusField);

        add(new JLabel("Contact:"));
        contactField = new JTextField();
        add(contactField);

        add(new JLabel("Address:"));
        addressField = new JTextField();
        add(addressField);

        JButton saveButton = new JButton("Save");
        add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        add(cancelButton);

        if (employee != null) {
            populateFields();
        }

        saveButton.addActionListener(e -> saveEmployee(parentPanel));
        cancelButton.addActionListener(e -> dispose());
    }

    private void populateFields() {
        fullNameField.setText(employee.getFullName());
        jobTitleField.setText(employee.getJobTitle());
        departmentField.setText(employee.getDepartment());
        statusField.setText(employee.getEmploymentStatus());
        contactField.setText(employee.getContactInformation());
        addressField.setText(employee.getAddress());
    }

    private void saveEmployee(EmployeePanel parentPanel) {
        try {
            Employee newEmployee = getEmployee();
                employeeService.createEmployee(newEmployee);

            parentPanel.loadData();
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving employee: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @NotNull
    private Employee getEmployee() {
        Employee newEmployee = employee == null ? new Employee() : employee;
        newEmployee.setFullName(fullNameField.getText());
        newEmployee.setJobTitle(jobTitleField.getText());
        newEmployee.setDepartment(departmentField.getText());
        newEmployee.setEmploymentStatus(statusField.getText());
        newEmployee.setContactInformation(contactField.getText());
        newEmployee.setAddress(addressField.getText());
        return newEmployee;
    }
}

