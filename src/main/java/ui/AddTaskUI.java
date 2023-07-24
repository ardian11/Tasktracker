package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class AddTaskUI extends JPanel {

    private ImageIcon icon;
    JButton addButton;

    public AddTaskUI(TrackerUI trackerUI){
        this.setBorder(new BevelBorder(0, new Color(196, 192, 192), new Color(188, 243, 226, 255)));
        this.setMinimumSize(new Dimension(trackerUI.getWidth(), 100));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBackground(new Color(255, 255, 255));

        icon = new ImageIcon(this.getClass().getResource("/add_task_icon.png"));
        addButton = new JButton();
        addButton.setPreferredSize(new Dimension(70, 70));
        addButton.setBackground(new Color(165, 192, 185));
        addButton.setPreferredSize(new Dimension(500, 100));
        addButton.setMaximumSize(new Dimension(trackerUI.getWidth(), 120));
        addButton.setIcon(icon);

        addButton.addActionListener( l -> {
            trackerUI.addTask();
        });

        this.add(addButton);
    }
}
