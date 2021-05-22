package logic.elements.complex;

import logic.WWElementGroup;
import logic.elements.Direction;
import logic.elements.simple.WWElementConductor;

import java.util.LinkedList;

public class WWElementDiode extends WWComplexElement {
    public WWElementDiode(int row, int column, Direction direction) {
        super(row, column, direction);
    }

    @Override
    public String toString() {
        return "Diode " + row + " " + column + " " + direction;
    }

    @Override
    public LinkedList<WWElementConductor> getConductors() {
        LinkedList<WWElementConductor> result = new LinkedList<>();
        int a, b;

        if (this.direction == Direction.RIGHT || this.direction == Direction.LEFT) {
            if (this.direction == Direction.RIGHT) {
                a = 1;
                b = 1;
            } else {
                a = -1;
                b = -1;
            }

            result.add(new WWElementConductor(this.row, this.column));
            result.add(new WWElementConductor(this.row, this.column + b));
            result.add(new WWElementConductor(this.row, this.column + 2 * b));
            result.add(new WWElementConductor(this.row, this.column + 3 * b));

            result.add(new WWElementConductor(this.row + a, this.column + 3 * b));
            result.add(new WWElementConductor(this.row - a, this.column + 3 * b));

            result.add(new WWElementConductor(this.row + a, this.column + 4 * b));
            result.add(new WWElementConductor(this.row - a, this.column + 4 * b));

            result.add(new WWElementConductor(this.row, this.column + 5 * b));
            result.add(new WWElementConductor(this.row, this.column + 6 * b));
            result.add(new WWElementConductor(this.row, this.column + 7 * b));
        } else {
            if (this.direction == Direction.UP) {
                a = -1;
                b = -1;
            } else {
                a = 1;
                b = 1;
            }

            result.add(new WWElementConductor(this.row, this.column));
            result.add(new WWElementConductor(this.row + a, this.column));
            result.add(new WWElementConductor(this.row + 2 * a, this.column));
            result.add(new WWElementConductor(this.row + 3 * a, this.column));

            result.add(new WWElementConductor(this.row + 3 * a, this.column+b));
            result.add(new WWElementConductor(this.row + 4 * a, this.column-b));

            result.add(new WWElementConductor(this.row + 3 * a, this.column-b));
            result.add(new WWElementConductor(this.row + 4 * a, this.column+b));

            result.add(new WWElementConductor(this.row+ 5 * a, this.column));
            result.add(new WWElementConductor(this.row+ 6 * a, this.column));
            result.add(new WWElementConductor(this.row+ 7 * a, this.column));

        }
        return result;
    }
}
