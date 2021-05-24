package logic;

import logic.elements.*;
import logic.elements.complex.*;
import logic.elements.simple.*;
import logic.exceptions.UnknownCommandException;
import logic.exceptions.UnknownWWElementDirectionException;
import logic.exceptions.UnknownWWElementNameException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Żeby dodać wykrywanie nowego elementu trzeba:
 * 1. dodać go do enum'a na dole
 * 2. w metodzie analyzeLine() dodać odpowiedni case
 * 3. w metodzie checkWWElemName() dodać if'a sprawdzającego nazwe
 */


abstract class Parser {

    public static WWElementGroup analyzeText(String text) {    //główna metoda, zwraca listę WWElement z rzeczami do narysowania
        WWElementGroup group = new WWElementGroup();
        String[] lines = text.split("\n");
        for (String line : lines) {
            if (line.replaceAll("\\s+", "").length() != 0)
                try {
                    group.add(analyzeLine(line));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
        }
        return group;
    }

    private static WWElement analyzeLine(String textLine) throws UnknownCommandException { //analiza jednej linijki tekstu
        WWElement result = null;
        String[] words = textLine.split(" ");
        int row, column;
        WWElementName name;
        Direction direction = Direction.RIGHT;

        if (words.length < 3 || words.length > 4)                               //jeśli jest za dużo lub za mało argumentów zwróć błąd
            throw new UnknownCommandException("Unknown command: " + textLine);

        try {
            name = checkWWElemName(words[0]);                                   //sprawdź co to za element
        } catch (UnknownWWElementNameException e) {
            throw new UnknownCommandException("Unknown command: " + textLine + " : " + e.getMessage());
        }

        try {
            row = Integer.parseInt(words[1]);                                   //pobierz rząd
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException("Unknown command: " + textLine + " : " + e.getMessage());
        }

        try {
            column = Integer.parseInt(words[2]);                                 //pobierz kolumnę
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException("Unknown command: " + textLine + " : " + e.getMessage());
        }

        if (words.length == 4) {
            try {
                direction = checkDirection(words[3]);
            } catch (UnknownWWElementDirectionException e) {
                throw new UnknownCommandException("Unknown command: " + textLine + " : " + e.getMessage());
            }
        }

        switch (name) {
            case ELECTRONHEAD:
                result = new WWElementElectronHead(row, column);
                System.out.println("New electron head: " + row + " " + column);
                break;
            case ELECTRONTAIL:
                result = new WWElementElectronTail(row, column);
                System.out.println("New electron tail: " + row + " " + column);
                break;
            case CONDUCTOR:
                result = new WWElementConductor(row, column);
                System.out.println("New conductor: " + row + " " + column);
                break;
            case DIODE:
                result = new WWElementDiode(row, column, direction);
                System.out.println("New diode: " + row + " " + column);
                break;
            case ORGATE:
                result = new WWElementORGate(row, column, direction);
                System.out.println("New OR gate: " + row + " " + column);
                break;
            case ANDGATE:
                result = new WWElementANDGate(row, column, direction);
                System.out.println("New AND gate: " + row + " " + column);
                break;
            case XORGATE:
                result = new WWElementXORGate(row, column, direction);
                System.out.println("New AND gate: " + row + " " + column);
                break;
            case FLIPFLOP:
                result = new WWElementFlipFlop(row, column, direction);
                System.out.println("New Flip Flop: " + row + " " + column);
                break;
        }
        return result;
    }

    private static WWElementName checkWWElemName(String s) {        //Sprawdza czy dany string opisuje konkretny element
        if (s.equalsIgnoreCase("ElectronHead")) return WWElementName.ELECTRONHEAD;
        else if (s.equalsIgnoreCase("ElectronTail")) return WWElementName.ELECTRONTAIL;
        else if (s.equalsIgnoreCase("Conductor")) return WWElementName.CONDUCTOR;
        else if (s.equalsIgnoreCase("Diode")) return WWElementName.DIODE;
        else if (s.equalsIgnoreCase("ORGate")) return WWElementName.ORGATE;
        else if (s.equalsIgnoreCase("ANDGate")) return WWElementName.ANDGATE;
        else if (s.equalsIgnoreCase("XORGate")) return WWElementName.XORGATE;
        else if (s.equalsIgnoreCase("FlipFlop")) return WWElementName.FLIPFLOP;
        else throw new UnknownWWElementNameException("Unknown WWElement: " + s);
    }

    private static Direction checkDirection(String s) {
        if (s.equalsIgnoreCase("UP")) return Direction.UP;
        else if (s.equalsIgnoreCase("DOWN")) return Direction.DOWN;
        else if (s.equalsIgnoreCase("LEFT")) return Direction.LEFT;
        else if (s.equalsIgnoreCase("RIGHT")) return Direction.RIGHT;
        else throw new UnknownWWElementDirectionException("Unknown WWElementDirection: " + s);
    }


    //main do testów
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setVisible(true);

        JTextArea area = new JTextArea(10, 10);
        f.add(area, BorderLayout.EAST);

        JTextArea out = new JTextArea(10, 10);
        f.add(out, BorderLayout.WEST);

        JButton test = new JButton("test");

        f.add(test, BorderLayout.CENTER);

        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WWElementGroup group = analyzeText(area.getText());
                out.setText("");
                for (WWElement entity : group.getElectronHeadList()) {
                    out.append(entity.toString() + "\n");
                }
                for (WWElement entity : group.getElectronTailList()) {
                    out.append(entity.toString() + "\n");
                }
                for (WWElement entity : group.getElemList()) {
                    out.append(entity.toString() + "\n");
                }
                for (WWElement entity : group.getAllConductorList()) {
                    out.append(entity.toString() + "\n");
                }

                out.append("MinRow: " + group.getMinRow() + "\n");
                out.append("MaxRow: " + group.getMaxRow() + "\n");
                out.append("MinCol: " + group.getMinColumn() + "\n");
                out.append("MaxCol: " + group.getMaxColumn() + "\n");
            }
        });


        f.pack();
    }

    private enum WWElementName {            //lista elementów możliwych do wykrycia
        ELECTRONHEAD,
        ELECTRONTAIL,
        CONDUCTOR,
        DIODE,
        ORGATE,
        ANDGATE,
        XORGATE,
        FLIPFLOP,
    }

}
