package gui;

import javax.swing.*;
import java.awt.*;

public class GUI {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel ControlPanel;


    private JButton startButton;
    private JButton saveButton;
    private JButton drawButton;
    private JButton openFileButton;

    private JTextField setNumberOfIterationsTextField;
    private JTextField terminalTextField;

    private Dimension size;
    private int width;
    private int height;

    public GUI() {
        size = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) size.getWidth();
        height = (int) size.getHeight();

        mainPanel = new JPanel();

        frame = new JFrame();
        frame.setSize(width/2, height/2);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("WireWorld");

        mainPanel.setLayout(new BorderLayout());

        showControlPanel();
        mainPanel.add(ControlPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void showControlPanel(){
        ControlPanel = new JPanel();
        ControlPanel.setLayout(new FlowLayout());
        ControlPanel.setMaximumSize(new Dimension(width/4, height));

        startButton = new JButton("Start");
        saveButton = new JButton("Save");

        setNumberOfIterationsTextField = new JTextField("Number of iterations", 15);

        drawButton = new JButton("Draw");
        openFileButton = new JButton("Open file");

        terminalTextField = new JTextField("Terminal", 15);

        ControlPanel.add(startButton, BorderLayout.EAST);
        ControlPanel.add(saveButton, BorderLayout.EAST);
        ControlPanel.add(setNumberOfIterationsTextField, BorderLayout.EAST);
        ControlPanel.add(drawButton, BorderLayout.EAST);
        ControlPanel.add(openFileButton, BorderLayout.EAST);
        ControlPanel.add(terminalTextField, BorderLayout.EAST);
    }


}
