package me.wappen.gamez.components.colliders;

import me.wappen.gamez.Rect;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 30.06.2021
 */

public class CircleCollider extends Collider {
    private float r;

    public CircleCollider(float r) {
        super(new Rect(0, 0, r * 2, r * 2));

        this.r = r;
    }

    @Override
    protected boolean checkCollision(Collider other) {
        if (other instanceof CircleCollider) {
            CircleCollider cc = (CircleCollider) other;
            return PVector.dist(getNode().getPos(), cc.getNode().getPos()) < (r + cc.r);
        }
        return false;
    }

    public float getR() {
        return r;
    }
}
