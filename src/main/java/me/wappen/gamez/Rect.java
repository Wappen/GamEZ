package me.wappen.gamez;

import processing.core.PVector;

/**
 * @author LenzK
 * @since 30.06.2021
 */

public class Rect {
    private PVector pos;
    private PVector size;

    public Rect(PVector pos, PVector size) {
        this.pos = pos;
        this.size = size;
    }

    public Rect(float x, float y, float w, float h) {
        this.pos = new PVector(x, y);
        this.size = new PVector(w, h);
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

    public float getLeft() {
        return pos.x;
    }

    public float getRight() {
        return pos.x + size.x;
    }

    public float getTop() {
        return pos.y;
    }

    public float getBottom() {
        return pos.y + size.y;
    }

    public Rect offset(PVector offset) {
        return new Rect(PVector.add(pos, offset), size);
    }

    public static boolean intersect(Rect r1, Rect r2) {
        return !(r2.getLeft() > r1.getRight() ||
                r2.getRight() < r1.getLeft() ||
                r2.getTop() > r1.getBottom() ||
                r2.getBottom() < r1.getTop());
    }
}
