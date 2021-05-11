package logic.elements.complex;

import logic.elements.Direction;

public class WWElementORGate extends WWComplexElement {
    public WWElementORGate(int row, int column, Direction direction) {
        super(row, column, direction);
    }

    @Override
    public String toString() {
        return "ORGate " + row + " " + column + " " + direction;
    }
}
