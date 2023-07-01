package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class TaskUI extends JPanel {


    private Timer timer;

    private long startTime;
    private long currentTime;
    private boolean started = false;

    private JComboBox<String> taskName;
    JTextField timerText;
    JButton start;
    JButton stop;

    public TaskUI(TrackerUI tracker){

        startTime = 0;
        currentTime = 0;

        this.setBorder(new BevelBorder(0, new Color(196, 192, 192), new Color(188, 243, 226, 255)));
        this.setPreferredSize(new Dimension(getWidth(), 100));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBackground(new Color(165, 192, 185));

        taskName = new JComboBox<>();
        taskName.setEditable(true);
        taskName.addItem("Task 1");
        taskName.addItem("Task 2");

        timerText = new JTextField("00:00");
        ActionListener updateTime = e -> {
            currentTime += 1;
            long seconds = currentTime % 60;
            long minutes = currentTime / 3600;
            timerText.setText(String.format("%02d:%02d", minutes, seconds));
        };
        timerText.addActionListener(updateTime);
        timer = new Timer(1000, updateTime);

        start = new JButton("Start");
        start.addActionListener(e -> {
            tracker.setCurrentTask(this);

            if(!started){
                timer.start();
                start.setText("Pause");
                started = true;
            }else{
                timer.stop();
                start.setText("Resume");
                started = false;
            }
        });
        stop = new JButton("Stop");
        stop.addActionListener(e -> {
            timer.stop();
            currentTime = 0;
            timerText.setText("00:00");
            started = false;
            start.setText("Start");
        });

        this.add(taskName);
        this.add(timerText);
        this.add(start);
        this.add(stop);
    }

}
