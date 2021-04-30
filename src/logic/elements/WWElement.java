package logic.elements;

public abstract class WWElement {
    protected int row;
    protected int column;
    protected boolean isReversed;

    public WWElement(int row, int column, boolean isReversed) {
        this.row = row;
        this.column = column;
        this.isReversed = isReversed;
    }

    public String toString() {
        return "xD";
    }

}
