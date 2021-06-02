package logic;

import gui.GUI;

public class WWController implements Runnable {
    private GUI gui;

    public WWBoard getBoard() {
        return board;
    }

    private WWBoard board;
    private WWElementGroup group;
    private Thread drawerThread;
    private int iterationsToDo;
    private int iterationDelay;
    private int minSleepTime;
    private int maxSleepTime;
    private final int boardSize;
    private volatile boolean isIterating;

    public WWController() {
        boardSize = 100;
        iterationDelay = 300;
        minSleepTime = 0;
        maxSleepTime = 600;
        gui = new GUI(this, boardSize);
    }

    public void generateBoard(String input) {
        isIterating = false;
        try {
            group = Parser.analyzeText(input);
            board = new WWBoard(this, group, boardSize);
            gui.drawBoard(board.getBoard());
            gui.setErrTextFieldText("");
        } catch (Exception e) {
            gui.setErrTextFieldText(e.getMessage());
        }
    }

    @Override
    public void run() {
        isIterating = true;
        for (int i = 0; i < iterationsToDo; i++) {
            if (!isIterating) return;
            long startTime = System.nanoTime();
            board.update();
            gui.drawBoard(board.getBoard());
            long elapsedTime = System.nanoTime() - startTime;

            try {
                long sleepTime = iterationDelay - elapsedTime / 1000000;
                //System.out.println(sleepTime);
                if (sleepTime < minSleepTime || i == iterationsToDo - 1) sleepTime = minSleepTime;
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

    public void setIterationSpeed(int speed) {
        iterationDelay = maxSleepTime - speed;
    }


    public int getMinSleepTime() {
        return minSleepTime;
    }

    public int getMaxSleepTime() {
        return maxSleepTime;
    }

}
