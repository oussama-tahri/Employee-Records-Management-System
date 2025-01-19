package com.employee.ui;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import com.employee.service.ApiClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmployeePanel extends JPanel {
    private final EmployeeService employeeService;
    private final JTable employeeTable;
    private final DefaultTableModel tableModel;

    public EmployeePanel() {
        employeeService = new EmployeeService(new ApiClient("http://localhost:8080/api"));

        setLayout(new BorderLayout());

        // Table Setup
        String[] columnNames = {"ID", "Full Name", "Job Title", "Department", "Status", "Contact", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0);
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load Data
        loadData();

        // Button Actions
        addButton.addActionListener(e -> openEmployeeForm(null));
        editButton.addActionListener(e -> editSelectedEmployee());
        deleteButton.addActionListener(e -> deleteSelectedEmployee());
    }

    public void loadData() {
        tableModel.setRowCount(0); // Clear table
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            for (Employee employee : employees) {
                tableModel.addRow(new Object[]{
                        employee.getFullName(), employee.getJobTitle(),
                        employee.getDepartment(),
                        employee.getEmploymentStatus(), employee.getContactInformation(), employee.getAddress()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openEmployeeForm(Employee employee) {
        new EmployeeForm(employeeService, employee, this).setVisible(true);
    }

    private void editSelectedEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to edit", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String employeeId = tableModel.getValueAt(selectedRow, 0).toString();
        Employee employee = employeeService.getEmployeeById(employeeId);
        openEmployeeForm(employee);
    }

    private void deleteSelectedEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String employeeId = tableModel.getValueAt(selectedRow, 0).toString();
        employeeService.deleteEmployee(employeeId);
        loadData();
    }
}