package logic;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class WWBoard {
    private int [][]board;
    private final int boardSize = 10;
    private List<Integer> electronList;
    private List<Integer> nextGenElectronList;
    private List<Integer> conductorList;
    private int electronX;
    private int electronY;
    private int conductorX;
    private int conductorY;

    public WWBoard(){
        board = new int[boardSize][boardSize];
        electronList = new ArrayList<Integer>();
        conductorList = new ArrayList<Integer>();
    }

    public void update(){
        nextGenElectronList = new ArrayList<Integer>();
        for(int i=0; i<electronList.size(); i++) {
            electronX=electronList.get(i);
            electronY=electronList.get(i++);
            findProperConductors(electronX, electronY);
            board[electronX][electronY] = 3; //ogon
        }
        electronList=nextGenElectronList;
    }

    private void findProperConductors(int electronX, int electronY) {
        for(int i=0; i<conductorList.size(); i++){
            conductorX=conductorList.get(i);
            conductorY=conductorList.get(i++);
            if(abs(electronX-conductorX)<2 || abs(electronY-conductorY)<2){
                if(countElectrons(conductorX, conductorY)<3){
                    board[conductorX][conductorY] = 2; //głowa
                    nextGenElectronList.add(conductorX);
                    nextGenElectronList.add(conductorY);
                }
            }
        }
    }

    private int countElectrons(int conductorX, int conductorY) {
        int electronCount=0;
        for(int i=0; i<electronList.size(); i++) {
            electronX=electronList.get(i);
            electronY=electronList.get(i++);
            if(abs(electronX-conductorX)<2 || abs(electronY-conductorY)<2){
                electronCount++;
            }
        }
        return  electronCount;
    }

    public void setTestValues(){
        board[2][1]=1;
        board[2][2]=2;
        board[2][3]=3;
    }

    public void displayBoardInTerminal(){
        for(int i=0; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
