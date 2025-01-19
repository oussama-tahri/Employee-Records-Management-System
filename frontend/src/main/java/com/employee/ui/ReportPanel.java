package com.employee.ui;

import com.employee.service.ReportService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportPanel extends JPanel {
    private final ReportService reportService;
    private final JTextArea reportTextArea;

    public ReportPanel() {
        reportService = new ReportService();

        setLayout(new BorderLayout());

        // Text Area for Reports
        reportTextArea = new JTextArea();
        reportTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton generateReportButton = new JButton("Generate Report");
        buttonPanel.add(generateReportButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Action
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });
    }

    private void generateReport() {
        try {
            String report = reportService.generateReport();
            reportTextArea.setText(report);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error generating report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}