package logic.elements.simple;

import logic.elements.WWElement;

public class WWElementElectronTail extends WWElement {
    public WWElementElectronTail(int row, int column) {
        super(row, column);
    }

    @Override
    public String toString() {
        return "ElectronTail " + row + " " + column;
    }
}
