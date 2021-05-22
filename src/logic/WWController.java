package logic;

import gui.GUI;

public class WWController implements Runnable {
    private GUI gui;
    private WWBoard board;
    private WWElementGroup group;
    private Thread drawerThread;
    private int iterationsToDo;
    private final int boardSize;
    private boolean isIterating;

    public WWController() {
        boardSize=10;
        gui = new GUI(this, boardSize);
    }

    public void generateBoard(String input) {

        board = new WWBoard(this, Parser.analyzeText(input),  boardSize);
        gui.drawBoard(group);
    }

    @Override
    public void run() {
        isIterating = true;
        for (int i = 0; i < iterationsToDo; i++) {
            board.update();
            gui.drawBoard(group);
        }
        isIterating = false;
    }

    public void iterate(int iterations) {
        if (!isIterating) {
            this.iterationsToDo = iterations;
            drawerThread = new Thread(this);
            drawerThread.start();
        }
    }

}