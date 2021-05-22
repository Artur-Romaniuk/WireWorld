package logic.elements.complex;

import logic.elements.Direction;
import logic.elements.WWElement;
import logic.elements.simple.WWElementConductor;

import java.util.LinkedList;

public abstract class WWComplexElement extends WWElement {
    Direction direction;

    WWComplexElement(int row, int column, Direction direction) {
        super(row, column);
        this.direction = direction;
    }

    public LinkedList<WWElementConductor> getConductors() {
        return null;
    }
}
