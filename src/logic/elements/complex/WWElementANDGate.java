package logic.elements.complex;

import logic.elements.Direction;

public class WWElementANDGate extends WWComplexElement {
    public WWElementANDGate(int row, int column, Direction direction) {
        super(row, column, direction);
    }

    @Override
    public String toString() {
        return "ANDGate " + row + " " + column + " " + direction;
    }
}
