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

    private JSpinner setNumberOfIterationsTextField;
    private JTextArea terminalTextArea;                 //parser input
    private JLabel errTextField;                        //parser error output

    private Dimension size;
    private int width;
    private int height;

    public GUI() {
        size = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) size.getWidth();
        height = (int) size.getHeight();

        mainPanel = new JPanel();

        frame = new JFrame();
        frame.setSize(width / 2, height / 2);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("WireWorld");

        mainPanel.setLayout(new BorderLayout());

        showControlPanel();
        mainPanel.add(controlPanel, BorderLayout.EAST);

        showWWBoard(10);
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        jButtonList.get(0).setBackground(Color.ORANGE);
        jButtonList.get(1).setBackground(Color.RED);

        frame.setVisible(true);
    }

    private void showControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        controlPanel.setMaximumSize(new Dimension(width / 2, height));

        JPanel controlSubPanel1 = new JPanel();
        JPanel controlSubPanel2 = new JPanel();
        JScrollPane terminalScrollPanel;

        controlSubPanel1.setLayout(new GridBagLayout());
        controlSubPanel2.setLayout(new GridBagLayout());

        startButton = new JButton("Start");
        saveButton = new JButton("Save");

        SpinnerModel values = new SpinnerNumberModel(1,0,99999,1);
        setNumberOfIterationsTextField = new JSpinner(values);

        drawButton = new JButton("Draw");
        openFileButton = new JButton("Open file");

        terminalTextArea = new JTextArea("Terminal",22,15);
        terminalScrollPanel = new JScrollPane(terminalTextArea);

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(3, 3, 3, 3);

        gbc1.gridx = 0;
        gbc1.gridy = 0;
        controlSubPanel1.add(startButton, gbc1);

        gbc1.gridx = 1;
        gbc1.gridy = 0;
        controlSubPanel1.add(saveButton, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 1;
        controlSubPanel1.add(new JLabel("Iterations:"), gbc1);

        gbc1.gridx = 1;
        gbc1.gridy = 1;
        controlSubPanel1.add(setNumberOfIterationsTextField, gbc1);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.insets = new Insets(3, 3, 3, 3);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        controlSubPanel2.add(drawButton, gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 0;
        controlSubPanel2.add(openFileButton, gbc2);

        errTextField = new JLabel();
        errTextField.setForeground(Color.RED);
        gbc2.gridwidth = 2;
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        controlSubPanel2.add(errTextField, gbc2);

        gbc2.gridwidth = 2;
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        controlSubPanel2.add(terminalScrollPanel, gbc2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);

        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(controlSubPanel1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        controlPanel.add(controlSubPanel2, gbc);

    }

    private void showWWBoard(int boardSize) {
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(10, 10));
        // boardPanel.setSize(650,650);
        //boardPanel.setMaximumSize(new Dimension(3*width/4, 3*height/2));
        boardPanel.setVisible(true);

        Color blackColor = Color.BLACK;
        JButton gridButton = null;
        jButtonList = new ArrayList<JButton>();

        for (int i = 0; i < boardSize * boardSize; i++) {
            gridButton = new JButton();
            gridButton.setBackground(blackColor);
            gridButton.setPreferredSize(new Dimension(10, 10));
            gridButton.setBorderPainted(true);
            gridButton.setRolloverEnabled(false);
            jButtonList.add(gridButton);
            boardPanel.add(gridButton);
        }
    }


}
