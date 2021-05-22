package logic;

import logic.elements.simple.WWElementConductor;
import logic.elements.simple.WWElementElectronHead;

import java.util.LinkedList;

import static java.lang.Math.abs;

public class WWBoard {
    private int[][] board;
    private final int boardSize;
    private LinkedList<WWElementElectronHead> nextGenElectronList;
    private WWElementGroup elementGroup;

    private WWElementElectronHead electron;
    private WWElementConductor conductor;

    private WWController controller;

    public WWBoard(WWController controller, WWElementGroup elementGroup, int boardSize) {
        this.controller = controller;
        this.boardSize = boardSize;
        board = new int[boardSize][boardSize];
        this.elementGroup = elementGroup;
        for (WWElementConductor conductor : elementGroup.getAllConductorList()) {
            board[conductor.getColumn()][conductor.getRow()] = 1;
        }
    }

    public void update() {
        nextGenElectronList = new LinkedList<WWElementElectronHead>();
        for (int i = 0; i < elementGroup.getElectronHeadList().size(); i++) {  //przejście po całej liście głów elektronów
            electron.setColumn(elementGroup.getElectronHeadList().get(i).getColumn()); //X i Y elektronu z listy
            electron.setRow(elementGroup.getElectronHeadList().get(i).getRow());
            findProperConductors(electron.getColumn(), electron.getRow()); //znajdź nowe miejsce na planszy
            board[electron.getColumn()][electron.getRow()] = 3; //ta glowa staje się ogonem
        }
        elementGroup.setElectronHeadList(nextGenElectronList);  //zamiana starej listy na nową
    }

    private void findProperConductors(int electronX, int electronY) {
        for (int i = 0; i < elementGroup.getAllConductorList().size(); i++) { //przejście po wszystkich przewodnikach
            conductor.setColumn(elementGroup.getAllConductorList().get(i).getColumn()); //x i y przewodnika
            conductor.setRow(elementGroup.getAllConductorList().get(i).getRow());
            if (abs(electronX - conductor.getColumn()) < 2 || abs(electronY - conductor.getRow()) < 2) { //jeżeli sąsiadują
                if (countElectrons(conductor.getColumn(), conductor.getRow()) < 3 && board[conductor.getColumn()][conductor.getRow()] == 1) {
                    board[conductor.getColumn()][conductor.getRow()] = 2; //głowa
                    nextGenElectronList.add(new WWElementElectronHead(conductor.getColumn(), conductor.getRow()));
                }
            }
        }
    }

    private int countElectrons(int conductorX, int conductorY) {
        int electronCount = 0;
        for (int i = 0; i < elementGroup.getElectronHeadList().size(); i++) {
            electron.setColumn(elementGroup.getElectronHeadList().get(i).getColumn());
            electron.setRow(elementGroup.getElectronHeadList().get(i).getRow());
            if (abs(electron.getColumn() - conductorX) < 2 || abs(electron.getRow() - conductorY) < 2) {
                electronCount++;
            }
        }
        return electronCount;
    }


}
