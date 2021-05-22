import gui.*;
import logic.Parser;
import logic.WWBoard;
import logic.WWElementGroup;

import java.lang.reflect.Parameter;

public class WireWorld {

    public WWElementGroup elementGroup;

    public static void main(String[] args){
        GUI gui = new GUI();
        WWBoard board = new WWBoard(Parser.analizeText("Diode 1 1"), 100);

    }
}
