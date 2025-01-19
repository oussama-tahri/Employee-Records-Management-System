package com.employee.ui;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JDialog {

    private final JComboBox<String> themeSelector;

    public SettingsDialog(JFrame parent) {
        super(parent, "Settings", true);
        setLayout(new BorderLayout());
        setSize(400, 200);
        setLocationRelativeTo(parent);

        // Theme Selector
        JPanel settingsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        settingsPanel.add(new JLabel("Select Theme:"));
        themeSelector = new JComboBox<>(new String[]{"Light", "Dark"});
        settingsPanel.add(themeSelector);

        add(settingsPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");

        btnSave.addActionListener(e -> saveSettings());
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveSettings() {
        String selectedTheme = (String) themeSelector.getSelectedItem();
        JOptionPane.showMessageDialog(this, "Settings saved: Theme - " + selectedTheme);
        // Logic to apply the theme can be implemented here
        dispose();
    }
}
