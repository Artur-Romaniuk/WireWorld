package logic.elements.complex;

import logic.elements.Direction;
import logic.elements.WWElement;

public class WWComplexElement extends WWElement {
    Direction direction;

    WWComplexElement(int row, int column, Direction direction) {
        super(row, column);
        this.direction = direction;
    }


}
