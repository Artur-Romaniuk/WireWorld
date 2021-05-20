package gui;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class GUI {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel controlPanel;
    private JPanel boardPanel;

    private List<JButton> jButtonList; //this is where jbuttons are stored, so you can change their properties later


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
        mainPanel.add(controlPanel, BorderLayout.NORTH);

        showWWBoard(10);
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        jButtonList.get(0).setBackground(Color.ORANGE);
        jButtonList.get(1).setBackground(Color.RED);

        frame.setVisible(true);
    }

    private void showControlPanel(){
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setMaximumSize(new Dimension(width/2, height));

        startButton = new JButton("Start");
        saveButton = new JButton("Save");

        setNumberOfIterationsTextField = new JTextField("Number of iterations", 15);

        drawButton = new JButton("Draw");
        openFileButton = new JButton("Open file");

        terminalTextField = new JTextField("Terminal", 15);

        controlPanel.add(startButton, BorderLayout.EAST);
        controlPanel.add(saveButton, BorderLayout.EAST);
        controlPanel.add(setNumberOfIterationsTextField, BorderLayout.EAST);
        controlPanel.add(drawButton, BorderLayout.EAST);
        controlPanel.add(openFileButton, BorderLayout.EAST);
        controlPanel.add(terminalTextField, BorderLayout.EAST);
    }

    private void showWWBoard(int boardSize){
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(10,10));
       // boardPanel.setSize(650,650);
        //boardPanel.setMaximumSize(new Dimension(3*width/4, 3*height/2));
        boardPanel.setVisible(true);

        Color blackColor = Color.BLACK;
        JButton gridButton = null;
        jButtonList = new ArrayList<JButton>();

        for(int i = 0; i< boardSize*boardSize; i++)
        {
            gridButton = new JButton();
            gridButton.setBackground(blackColor);
            gridButton.setPreferredSize(new Dimension(10,10));
            gridButton.setBorderPainted(true);
            gridButton.setRolloverEnabled(false);
            jButtonList.add(gridButton);
            boardPanel.add(gridButton);
        }
    }


}
