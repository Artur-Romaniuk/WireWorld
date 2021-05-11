package logic.elements.complex;

import logic.elements.Direction;

public class WWElementXORGate extends WWComplexElement {
    public WWElementXORGate(int row, int column, Direction direction) {
        super(row, column, direction);
    }

    @Override
    public String toString() {
        return "XORGate " + row + " " + column + " " + direction;
    }
}
