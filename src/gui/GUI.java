package gui;

import io.WWIO;
import logic.WWController;
import logic.WWElementGroup;
import logic.elements.simple.WWElementConductor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
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
    private JButton saveTerminalButton;
    private JButton drawButton;
    private JButton openFileButton;

    private JSpinner setNumberOfIterationsTextField;
    private JSlider setIterationSpeedSlider;
    private JTextArea terminalTextArea;                 //parser input
    private JLabel errTextField;                        //parser error output

    private Dimension size;
    private int width;
    private int height;

    private int boardSize;

    WWController controller;

    public GUI(WWController controller, int boarSize) {
        this.boardSize = boarSize;
        this.controller = controller;
        size = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) size.getWidth();
        height = (int) size.getHeight();

        mainPanel = new JPanel();

        frame = new JFrame();
        frame.setSize(width / 2, height / 2);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("WireWorld");
        frame.setResizable(false);

        mainPanel.setLayout(new BorderLayout());

        showControlPanel();
        mainPanel.add(controlPanel, BorderLayout.EAST);

        showWWBoard();
        mainPanel.add(boardPanel, BorderLayout.CENTER);


        frame.setVisible(true);
        frame.pack();
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

        SpinnerModel values = new SpinnerNumberModel(1, 0, 99999, 1);
        setNumberOfIterationsTextField = new JSpinner(values);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.iterate((int) setNumberOfIterationsTextField.getValue());
            }
        });

        saveButton = new JButton("Save generation");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        saveTerminalButton = new JButton("Save Terminal");
        saveTerminalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        WWIO.saveTerminalToFile(file, terminalTextArea.getText());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
            }
        });

        drawButton = new JButton("Draw");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.generateBoard(terminalTextArea.getText());
            }
        });
        openFileButton = new JButton("Open file");
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        List<String> fileText = WWIO.readFileAndSaveToTerminal(file);
                        terminalTextArea.setText("");
                        for (String textLine : fileText) {
                            terminalTextArea.append(textLine + "\n");
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
            }
        });

        setIterationSpeedSlider = new JSlider(SwingConstants.HORIZONTAL, 1, 599, 300);
        setIterationSpeedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                controller.changeIterationSpeed((int) source.getValue());
            }
        });

        terminalTextArea = new JTextArea("Diode 10 10\nElectronHead 10 10", 22, 15);
        terminalScrollPanel = new JScrollPane(terminalTextArea);

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(3, 3, 3, 3);

        gbc1.gridx = 0;
        gbc1.gridy = 0;
        controlSubPanel1.add(new JLabel("Iteration speed:"), gbc1);

        gbc1.gridwidth = 2;
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        controlSubPanel1.add(setIterationSpeedSlider, gbc1);

        gbc1.gridwidth = 1;
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        controlSubPanel1.add(startButton, gbc1);

        gbc1.gridx = 1;
        gbc1.gridy = 2;
        controlSubPanel1.add(saveButton, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 3;
        controlSubPanel1.add(new JLabel("Iterations:"), gbc1);

        gbc1.gridx = 1;
        gbc1.gridy = 3;
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

        gbc2.gridx = 1;
        gbc2.gridy = 1;
        controlSubPanel2.add(saveTerminalButton, gbc2);

        errTextField = new JLabel();
        errTextField.setForeground(Color.RED);
        gbc2.gridwidth = 2;
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        controlSubPanel2.add(errTextField, gbc2);

        gbc2.gridwidth = 2;
        gbc2.gridx = 0;
        gbc2.gridy = 3;
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

    private void showWWBoard() {
        boardPanel = new JPanel();
        JPanel bombGrid = new JPanel();
        JButton gridButton = null;
        jButtonList = new ArrayList<JButton>();

        Color blackColor = Color.BLACK;
        Color gridColor = new Color(20,20,20);

        boardPanel.add(bombGrid);
        bombGrid.setLayout(new GridLayout(boardSize, boardSize,1,1));
        bombGrid.setBackground(gridColor);
        // boardPanel.setSize(650,650);
        //boardPanel.setMaximumSize(new Dimension(3*width/4, 3*height/2));
        bombGrid.setVisible(true);



        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                gridButton = new JButton();
                gridButton.setBackground(blackColor);
                gridButton.setPreferredSize(new Dimension(9, 9));
                gridButton.setBorderPainted(false);
                gridButton.setRolloverEnabled(false);

                gridButton.setToolTipText(i+" "+j);
                jButtonList.add(gridButton);
                bombGrid.add(gridButton);
            }
        }
    }

    public void drawBoard(int[][] board) {
        int val;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                val = board[j][i];
                if (val == 0) {
                    jButtonList.get(i * boardSize + j).setBackground(Color.BLACK);
                } else if (val == 1) {
                    jButtonList.get(i * boardSize + j).setBackground(Color.ORANGE);
                } else if (val == 2) {
                    jButtonList.get(i * boardSize + j).setBackground(Color.BLUE);
                } else if (val == 3) {
                    jButtonList.get(i * boardSize + j).setBackground(Color.RED);
                }
            }
        }
    }


}
