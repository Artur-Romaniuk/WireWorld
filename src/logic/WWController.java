package logic;

import gui.GUI;

public class WWController implements Runnable {
    private GUI gui;

    public WWBoard getBoard() {
        return board;
    }

    private WWBoard board;
    private Thread drawerThread;
    private int iterationsToDo;
    private int iterationDelay;
    private final int boardSize;
    private boolean isIterating;

    public WWController() {
        boardSize = 100;
        iterationDelay = 300;
        gui = new GUI(this, boardSize);
    }

    public void generateBoard(String input) {

        board = new WWBoard(this, Parser.analyzeText(input), boardSize);
        gui.drawBoard(board.getBoard());
    }

    @Override
    public void run() {
        isIterating = true;
        for (int i = 0; i < iterationsToDo; i++) {


            long startTime = System.nanoTime();
            board.update();
            gui.drawBoard(board.getBoard());
            long elapsedTime = System.nanoTime() - startTime;

            try {
                long sleepTime = iterationDelay - elapsedTime / 1000000;
                if (sleepTime < 0 || i==iterationsToDo-1) sleepTime = 0;
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public void changeIterationSpeed(int speed) {
        iterationDelay = 615 - speed;
    }

}
