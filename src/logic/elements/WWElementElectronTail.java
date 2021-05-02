package logic.elements;

public class WWElementElectronTail extends WWElement {
    public WWElementElectronTail(int row, int column) {
        super(row, column, false);
    }

    @Override
    public String toString() {
        return "ElectronTail " + row + " " + column;
    }
}
