package me.wappen.gamez.components.shapes;

import me.wappen.gamez.Window;
import me.wappen.gamez.components.Component;
import processing.core.PShape;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public class RectangleShape extends Component {
    PVector size;
    int col;
    PShape rect;

    public RectangleShape(int w, int h, int col) {
        size = new PVector(w, h);
        this.col = col;
        rect = Window.getInstance().createShape(PShape.RECT, 0, 0, w, h);
        rect.setFill(col);
    }

    @Override
    public PShape getShape() {
        return rect;
    }
}
