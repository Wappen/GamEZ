package me.wappen.gamez.components.shapes;

import me.wappen.gamez.components.Component;
import processing.core.PShape;

/**
 * @author LenzK
 * @since 30.06.2021
 */

public abstract class Shape extends Component {
    public abstract PShape getShape();
}
