package logic.elements;

public abstract class WWElement {
    protected int row;
    protected int column;

    public WWElement(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
