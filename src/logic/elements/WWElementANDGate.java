package logic.elements;

public class WWElementANDGate extends WWElement {
    public WWElementANDGate(int row, int column, boolean isReversed) {
        super(row, column, isReversed);
    }

    @Override
    public String toString() {
        if (isReversed) {
            return "ANDGate " + row + " " + column + " " + "reversed";
        } else
            return "ANDGate " + row + " " + column + " " + "normal";
    }
}
