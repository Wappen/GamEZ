package me.wappen.gamez.components.shapes;

import me.wappen.gamez.Window;
import processing.core.PShape;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public class CircleShape extends Shape {
    int r;
    int col;
    PShape circ;

    public CircleShape(int r, int col) {
        this.r = r;
        this.col = col;
        circ = Window.getInstance().createShape(PShape.ELLIPSE, 0, 0, r * 2, r * 2);
        circ.setFill(col);
    }

    @Override
    public PShape getShape() {
        return circ;
    }
}
