package com.employee.ui;

import com.employee.service.EmployeeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SearchPanel extends JPanel {

    private final JTextField txtSearch;
    private final JTable table;

    public SearchPanel(JTable table) {
        this.table = table;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Search"));

        txtSearch = new JTextField();
        add(txtSearch, BorderLayout.CENTER);

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterTable(txtSearch.getText());
            }
        });
    }



    private void filterTable(String query) {
        if (table.getModel() instanceof DefaultTableModel model) {
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query)); // Case-insensitive filtering
        }
    }
}
