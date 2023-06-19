package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class TrackerUI extends JFrame {

    private JPanel headerPanel;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;

    public TrackerUI(){
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

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

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
}


