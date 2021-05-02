package logic.elements;

public class WWElementORGate extends WWElement {
    public WWElementORGate(int row, int column, boolean isReversed) {
        super(row, column, isReversed);
    }

    @Override
    public String toString() {
        if (isReversed) {
            return "ORGate " + row + " " + column + " " + "Reversed";
        } else
            return "ORGate " + row + " " + column + " " + "Normal";
    }
}
