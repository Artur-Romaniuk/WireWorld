package logic.elements;

public abstract class WWElement {
    protected int row;
    protected int column;

    public WWElement(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
