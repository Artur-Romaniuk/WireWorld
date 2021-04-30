package logic.elements;

public class WWElementDiode extends WWElement {
    public WWElementDiode(int row, int column, boolean isReversed) {
        super(row, column, isReversed);
    }

    @Override
    public String toString() {
        if (isReversed) {
            return "Diode " + row + " " + column + " " + "reversed";
        } else
            return "Diode " + row + " " + column + " " + "normal";
    }
}
