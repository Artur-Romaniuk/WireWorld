package logic.elements;

public class WWElementElectronHead extends WWElement {
    public WWElementElectronHead(int row, int column) {
        super(row, column, false);
    }

    @Override
    public String toString() {
        return "ElectronHead " + row + " " + column;
    }
}
