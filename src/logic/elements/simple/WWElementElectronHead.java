package logic.elements.simple;

import logic.elements.WWElement;

public class WWElementElectronHead extends WWElement {
    public WWElementElectronHead(int row, int column) {
        super(row, column);
    }

    @Override
    public String toString() {
        return "ElectronHead " + row + " " + column;
    }
}
