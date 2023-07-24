package ui;

import db.DataType.Preferences;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingsUI extends JFrame {

    private Preferences preferences;

    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JTextField textField1;
    private JLabel label1;

    public SettingsUI(Preferences preferences) {
        // Set up the main frame

        this.preferences = preferences;
        setTitle("Settings");
        setSize(300, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Create the checkboxes
        checkBox1 = new JCheckBox("Auto Pause", preferences.getAutoPause());
        checkBox2 = new JCheckBox("AFK-Warning", preferences.afkWarning());

        label1 = new JLabel("Round Minutes To: ");
        textField1 = new JTextField(Integer.toString(preferences.getRoundMinutesTo()));

        // Add the checkboxes to the panel
        panel.add(checkBox1);
        panel.add(checkBox2);
        panel.add(label1);
        panel.add(textField1);

        // Create the button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            saveSettings();
            dispose();
        });

        // Add the panel and button to the frame
        add(panel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void saveSettings() {
        // Retrieve the state of the checkboxes and save the settings
        boolean option1Selected = checkBox1.isSelected();
        boolean option2Selected = checkBox2.isSelected();

        // Implement your logic to save the settings here
        // For now, we'll just print the selected options
        System.out.println("Option 1 selected: " + option1Selected);
        System.out.println("Option 2 selected: " + option2Selected);

    }
}
