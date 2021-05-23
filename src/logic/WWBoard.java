package logic;

import logic.elements.simple.WWElementConductor;
import logic.elements.simple.WWElementElectronHead;
import logic.elements.simple.WWElementElectronTail;

import java.util.LinkedList;

import static java.lang.Math.abs;

public class WWBoard {
    private int[][] board;
    private LinkedList<WWElementElectronHead> nextGenElectronHeadList;
    private LinkedList<WWElementElectronTail> nextGenElectronTailList;
    private WWElementGroup elementGroup;

    private WWElementElectronHead electron;
    private WWElementConductor conductor;

    private WWController controller;

    public WWBoard(WWController controller, WWElementGroup elementGroup, int boardSize) {
        this.controller = controller;
        board = new int[boardSize][boardSize];
        this.elementGroup = elementGroup;
        for (WWElementConductor conductor : elementGroup.getAllConductorList()) {
            board[conductor.getColumn()][conductor.getRow()] = 1;
        }
        for (WWElementElectronHead electronHead:elementGroup.getElectronHeadList()) {
            board[electronHead.getColumn()][electronHead.getRow()] = 2;
        }
        for (WWElementElectronTail electronTail:elementGroup.getElectronTailList()) {
            board[electronTail.getColumn()][electronTail.getRow()] = 3;
        }
        electron = new WWElementElectronHead(0,0);
        conductor = new WWElementConductor(0,0);
    }

    public void update() {
        nextGenElectronHeadList = new LinkedList<WWElementElectronHead>();
        nextGenElectronTailList = new LinkedList<WWElementElectronTail>();

        System.out.println(elementGroup.getElectronHeadList().size());

        for (int i = 0; i < elementGroup.getElectronHeadList().size(); i++) {  //przejście po całej liście głów elektronów
            electron.setColumn(elementGroup.getElectronHeadList().get(i).getColumn()); //X i Y elektronu z listy
            electron.setRow(elementGroup.getElectronHeadList().get(i).getRow());
            findProperConductors(electron); //znajdź nowe miejsce na planszy

            board[electron.getColumn()][electron.getRow()] = 3; //ta glowa staje się ogonem
            nextGenElectronTailList.add(new WWElementElectronTail(electron.getRow(), electron.getColumn()));
        }
        for (WWElementElectronTail electron:elementGroup.getElectronTailList()) {
            board[electron.getColumn()][electron.getRow()]=1;
        }
        elementGroup.setElectronTailList(nextGenElectronTailList);
        elementGroup.setElectronHeadList(nextGenElectronHeadList);  //zamiana starej listy na nową
    }

    private void findProperConductors(WWElementElectronHead electron) {
        for (int i = 0; i < elementGroup.getAllConductorList().size(); i++) { //przejście po wszystkich przewodnikach
            conductor.setColumn(elementGroup.getAllConductorList().get(i).getColumn()); //x i y przewodnika
            conductor.setRow(elementGroup.getAllConductorList().get(i).getRow());
            if (abs(electron.getColumn() - conductor.getColumn()) < 2 && abs(electron.getRow() - conductor.getRow()) < 2) {
                //jeżeli sąsiadują
                if (countElectrons(conductor) < 3 && board[conductor.getColumn()][conductor.getRow()] == 1) {
                    board[conductor.getColumn()][conductor.getRow()] = 2; //głowa
                    nextGenElectronHeadList.add(new WWElementElectronHead(conductor.getRow(), conductor.getColumn()));
                }
            }
        }
    }

    private int countElectrons(WWElementConductor conductor) {
        int electronCount = 0;
        WWElementElectronHead electron = new WWElementElectronHead(0,0);
        for (int i = 0; i < elementGroup.getElectronHeadList().size(); i++) {
            electron.setColumn(elementGroup.getElectronHeadList().get(i).getColumn());
            electron.setRow(elementGroup.getElectronHeadList().get(i).getRow());
            if (abs(electron.getColumn() - conductor.getColumn()) < 2 && abs(electron.getRow() - conductor.getRow()) < 2) {
                electronCount++;
            }
        }
        return electronCount;
    }

    public int [][] getBoard(){
        return board;
    }

}
