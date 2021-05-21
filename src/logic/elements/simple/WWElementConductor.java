package logic.elements.simple;

import logic.elements.WWElement;

public class WWElementConductor extends WWElement {
    public WWElementConductor(int row, int column) {
        super(row, column);
    }

    @Override
    public String toString() {
        return "Conductor " + row + " " + column;
    }
}
