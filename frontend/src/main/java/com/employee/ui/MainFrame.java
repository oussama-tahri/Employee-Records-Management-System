package com.employee.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final EmployeePanel employeePanel;
    private final ReportPanel reportPanel;

    public MainFrame() {
        setTitle("Employee Records Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize panels
        employeePanel = new EmployeePanel();
        reportPanel = new ReportPanel();

        // Add navigation bar
        JPanel navigationPanel = createNavigationPanel();
        add(navigationPanel, BorderLayout.WEST);

        // Add default panel (Employee Panel)
        add(employeePanel, BorderLayout.CENTER);
    }

    private JPanel createNavigationPanel() {
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new GridLayout(5, 1));
        navigationPanel.setPreferredSize(new Dimension(200, 0));

        JButton btnEmployee = new JButton("Manage Employees");
        JButton btnReports = new JButton("Generate Reports");
        JButton btnLogout = new JButton("Logout");

        btnEmployee.addActionListener(e -> switchPanel(employeePanel));
        btnReports.addActionListener(e -> switchPanel(reportPanel));

        navigationPanel.add(btnEmployee);
        navigationPanel.add(btnReports);
        navigationPanel.add(btnLogout);

        return navigationPanel;
    }

    private void switchPanel(JPanel panel) {
        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        add(createNavigationPanel(), BorderLayout.WEST);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
