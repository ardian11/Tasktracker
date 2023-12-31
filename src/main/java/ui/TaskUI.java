package ui;

import db.Task;
import ui.support.ComboBoxKeyListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TaskUI extends JPanel {


    private Timer timer;

    private long startTime;
    private long currentTime;
    private boolean started = false;

    private JComboBox<String> taskNameBox;

    private String savedTaskName;
    JTextField timerText;
    JButton start;
    JButton stop;

    JCheckBox mark;

    private Task task;

    public TaskUI(TrackerUI tracker, ArrayList<String> taskNames){

        startTime = 0;
        currentTime = 0;

        this.setBorder(new BevelBorder(0, new Color(196, 192, 192), new Color(188, 243, 226, 255)));
        this.setPreferredSize(new Dimension(getWidth(), 100));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBackground(new Color(165, 192, 185));

        this.taskNameBox = new JComboBox<>();
        this.taskNameBox.setMinimumSize(new Dimension(500, this.taskNameBox.getHeight()));
        this.taskNameBox.setEditable(true);
        this.taskNameBox.insertItemAt("", 0);
        for (String name : taskNames){
            this.taskNameBox.addItem(name);
        }

        ComboBoxKeyListener comboBoxKeyListener = new ComboBoxKeyListener(this, taskNames);
        this.taskNameBox.getEditor().getEditorComponent().addKeyListener(comboBoxKeyListener);
        this.taskNameBox.addActionListener(l->{
            System.out.println("manualInput: " + comboBoxKeyListener.isManualInput());
            if(!comboBoxKeyListener.isManualInput()){
                comboBoxKeyListener.setInputBuffer(taskNameBox.getSelectedItem().toString());
            }
            comboBoxKeyListener.searchTask();
        });

        timerText = new JTextField("00:00:00");
        ActionListener updateTime = e -> {
            currentTime += 1;
            long seconds = currentTime % 60;
            long minutes = currentTime % 3600 / 60 ;
            long hours = currentTime / 3600;
            timerText.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
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
            timerText.setText("00:00:00");
            started = false;
            start.setText("Start");
        });

        mark = new JCheckBox("Remember task");
        mark.setBackground(this.getBackground());

        this.add(this.taskNameBox);
        this.add(timerText);
        this.add(start);
        this.add(stop);
        this.add(mark);
    }

    public void loadTask(String taskName){
        System.out.println("Loading task: " + taskName);
    }

    public JCheckBox getMark() {
        return mark;
    }

    public void setSavedTaskName(String savedTaskName) {
        this.savedTaskName = savedTaskName;
    }

    public String getSavedTaskName() {
        return savedTaskName;
    }

    public JComboBox<String> getTaskNameBox() {
        return taskNameBox;
    }
}
