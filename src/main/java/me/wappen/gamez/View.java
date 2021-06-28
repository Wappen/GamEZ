package me.wappen.gamez;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public class View {
    private int color;
    private PVector pos;
    private PVector size;

    public View(PVector pos, PVector size) {
        this.color = 0;
        this.pos = pos;
        this.size = size;
    }

    public void apply(PApplet win) {
        win.background(color);
        win.pushMatrix();
        win.translate(pos.x, pos.y);
        win.scale(size.x / win.width, size.y / win.height);
    }

    public void revert(PApplet win) {
        win.popMatrix();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public PVector getPos() {
        return pos;
    }

    public void setPos(PVector pos) {
        this.pos = pos;
    }

    public PVector getSize() {
        return size;
    }

    public void setSize(PVector size) {
        this.size = size;
    }
}
