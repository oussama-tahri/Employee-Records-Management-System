package com.employee;

import com.employee.ui.EmployeePanel;
import com.employee.ui.ReportPanel;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    public MainFrame() {
            setTitle("Employee Records Management System");
            setSize(1000, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Employees", new EmployeePanel());
            tabbedPane.addTab("Reports", new ReportPanel());

            add(tabbedPane, BorderLayout.CENTER);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            });
        }
    }

