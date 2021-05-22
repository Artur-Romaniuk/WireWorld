import gui.*;
import logic.Parser;
import logic.WWBoard;
import logic.WWElementGroup;

public class WireWorld {

    private static WWElementGroup elementGroup;
    private static GUI gui;
    private static WWBoard board;

    public static void main(String[] args) {
        gui = new GUI();
        board = new WWBoard(Parser.analyzeText("Diode 1 1"), 100);

    }

}
