package com.employee.ui;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {

    public SplashScreen() {
        JLabel splashLabel = new JLabel("Employee Records Management System", SwingConstants.CENTER);
        splashLabel.setFont(new Font("Arial", Font.BOLD, 30));
        splashLabel.setForeground(Color.WHITE);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.DARK_GRAY);
        content.add(splashLabel, BorderLayout.CENTER);

        setContentPane(content);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.setVisible(true);

        // Simulate loading process
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splashScreen.dispose();
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}