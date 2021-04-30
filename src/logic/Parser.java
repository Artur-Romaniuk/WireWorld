package logic;

import logic.elements.WWElement;
import logic.elements.WWElementANDGate;
import logic.elements.WWElementDiode;
import logic.elements.WWElementORGate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Parser {

    static String[] WWElemNames = {"Diode", "ANDGate,ORGate"};//elementy do wykrywania w tekście
    static String[] Keywords = {"Reversed"};

    public static WWElementGroup analizeText(String text) {    //główna metoda, zwraca listę WWElement z rzeczami do narysowania
        WWElementGroup group = new WWElementGroup();
        String[] lines = text.split("\n");
        for (String line : lines) {
            if (line.replaceAll("\\s+", "").length() != 0)
                try {
                    group.add(analizeLine(line));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
        }
        return group;
    }

    private static WWElement analizeLine(String textLine) throws UnknownCommandException { //analiza jednej linijki tekstu
        WWElement result = null;

        String[] words = textLine.split(" ");

        if (words.length < 3 || words.length > 4)
            throw new UnknownCommandException("Unknown command: " + textLine);

        int row, column;
        boolean isReversed = false;
        WWElementName name;
        try {
            name = checkWWElemName(words[0]);
        } catch (UnknownWWElementNameException e) {
            throw new UnknownCommandException("Unknown command: " + textLine + " " + e.getMessage());
        }

        row = Integer.parseInt(words[1]);
        column = Integer.parseInt(words[2]);

        if (words.length == 4)
            isReversed = words[3].compareTo("Reversed") == 0;


        switch (name) {
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

    private static WWElementName checkWWElemName(String s) {
        if (s.compareTo("Diode") == 0) {
            return WWElementName.DIODE;
        } else if (s.compareTo("ORGate") == 0) {
            return WWElementName.ORGATE;
        } else if (s.compareTo("ANDGate") == 0) {
            return WWElementName.ANDGATE;
        } else {
            throw new UnknownWWElementNameException("Unknown WWElement: " + s);
        }
    }


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

    private enum WWElementName {
        DIODE,
        ORGATE,
        ANDGATE,
    }

}
