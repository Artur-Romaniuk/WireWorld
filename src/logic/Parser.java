package logic;

import logic.elements.*;

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


public class Parser {

    public static WWElementGroup analizeText(String text) {    //główna metoda, zwraca listę WWElement z rzeczami do narysowania
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
        boolean isReversed = false;
        WWElementName name;

        if (words.length < 3 || words.length > 4)
            throw new UnknownCommandException("Unknown command: " + textLine);

        try {
            name = checkWWElemName(words[0]);
        } catch (UnknownWWElementNameException e) {
            throw new UnknownCommandException("Unknown command: " + textLine + " : " + e.getMessage());
        }

        try {
            row = Integer.parseInt(words[1]);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException("Unknown command: " + textLine + " : " + e.getMessage());
        }

        try {
            column = Integer.parseInt(words[2]);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException("Unknown command: " + textLine + " : " + e.getMessage());
        }

        if (words.length == 4) {
            if ( words[3].compareTo("Reversed") == 0) {
                isReversed = true;
            } else if (words[3].compareTo("Normal") == 0) {
                isReversed = false;
            } else {
                throw new UnknownCommandException("Unknown command: " + textLine + " : " + "Unknown argument: " + words[3]);
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
            case DIODE:
                result = new WWElementDiode(row, column, isReversed);
                System.out.println("New diode: " + row + " " + column);
                break;
            case ORGATE:
                result = new WWElementORGate(row, column, isReversed);
                System.out.println("New OR gate: " + row + " " + column);
                break;
            case ANDGATE:
                result = new WWElementANDGate(row, column, isReversed);
                System.out.println("New AND gate: " + row + " " + column);
                break;
        }
        return result;
    }

    private static WWElementName checkWWElemName(String s) {        //Sprawdza czy dany string opisuje konkretny element
        if (s.compareTo("ElectronHead") == 0) return WWElementName.ELECTRONHEAD;
        else if (s.compareTo("ElectronTail") == 0) return WWElementName.ELECTRONTAIL;
        else if (s.compareTo("Diode") == 0) return WWElementName.DIODE;
        else if (s.compareTo("ORGate") == 0) return WWElementName.ORGATE;
        else if (s.compareTo("ANDGate") == 0) return WWElementName.ANDGATE;
        else throw new UnknownWWElementNameException("Unknown WWElement: " + s);
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
                WWElementGroup group = analizeText(area.getText());
                out.setText("");
                for (WWElement entity : group) {
                    out.append(entity.toString());
                }
            }
        });


        f.pack();
    }

    private enum WWElementName {            //lista elementów możliwych do narysowania
        ELECTRONHEAD,
        ELECTRONTAIL,
        DIODE,
        ORGATE,
        ANDGATE,
    }

}
