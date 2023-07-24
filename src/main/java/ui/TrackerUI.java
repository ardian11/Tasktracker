package ui;

import db.Task;
import executable.TaskTracker;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class TrackerUI extends JFrame {

    private JPanel headerPanel;
    private JScrollPane scrollPane;

    private JPanel contentPanel;

    private ArrayList<Task> taskList;


    private TaskUI currentTask;

    private ArrayList<String> taskNames;

    private AddTaskUI addTaskUI;


    public TrackerUI(TaskTracker tracker){
        setTitle("TaskTracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 400));

        ImageIcon icon = new  ImageIcon(this.getClass().getResource("/tasktracker_icon_128.png"));
        setIconImage(icon.getImage());



        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            //TODO
        });

        JButton settings = new JButton("Settings");

        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonContainer.add(saveButton);
        buttonContainer.add(settings);
        headerPanel.add(buttonContainer, BorderLayout.NORTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        taskList = tracker.getAllTasks();

        taskNames = new ArrayList<>();
        taskList.stream().forEach(e->{
            taskNames.add(e.getName());
        });

        contentPanel.add(new TaskUI(this, taskNames));
        contentPanel.add(addTaskUI = new AddTaskUI(this));

        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private static final String IMAGE_NAME =
            "/tasktracker_icon_128.png";

    private static ImageIcon getIcon() {
        return new ImageIcon(Toolkit.getDefaultToolkit()
                .getImage(IMAGE_NAME)) {
            @Override
            public int getIconWidth() {
                return 18;
            }
            @Override
            public int getIconHeight() {
                return 18;
            }

            @Override
            public synchronized void paintIcon(Component c, Graphics g,
                                               int x, int y) {
                g.drawImage(getImage(), x, y, 18, 18, null);
            }
        };
    }

    public void setCurrentTask(TaskUI task) {
        if(currentTask != null) {
            currentTask.setBorder(new BevelBorder(0, new Color(196, 192, 192), new Color(188, 243, 226, 255)));
            currentTask.setBackground(new Color(165, 192, 185));
            currentTask.getMark().setBackground(new Color(165, 192, 185));
        }

        currentTask = task;
        currentTask.setBorder(new BevelBorder(0, new Color(94, 194, 152), new Color(40, 84, 65, 255)));
        currentTask.setBackground(new Color(198, 232, 223));
        currentTask.getMark().setBackground(new Color(198, 232, 223));

    }

    public void addTask(){
        contentPanel.add(new TaskUI(this, taskNames));
        contentPanel.remove(addTaskUI);
        contentPanel.add(addTaskUI);
        contentPanel.revalidate();
    }
}


