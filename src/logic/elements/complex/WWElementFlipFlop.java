package logic.elements.complex;

import logic.elements.Direction;

public class WWElementFlipFlop extends WWComplexElement {
    public WWElementFlipFlop(int row, int column, Direction direction) {
        super(row, column, direction);
    }

    @Override
    public String toString() {
        return "FlipFlop " + row + " " + column + " " + direction;
    }
}
