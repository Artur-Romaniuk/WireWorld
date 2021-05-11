package logic.elements.complex;

import logic.elements.Direction;

public class WWElementDiode extends WWComplexElement {
    public WWElementDiode(int row, int column, Direction direction) {
        super(row, column, direction);
    }

    @Override
    public String toString() {
        return "Diode " + row + " " + column + " " + direction;
    }
}
