package me.wappen.gamez.components.colliders;

import me.wappen.gamez.Collision;
import me.wappen.gamez.Rect;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 30.06.2021
 */

public class CircleCollider extends Collider {
    private float radius;

    public CircleCollider(float r) {
        super(new Rect(-r, -r, r * 2, r * 2));

        this.radius = r;
    }

    @Override
    public void onCollide(Collision collision) {
        Collider other = collision.getC2();

        if (other instanceof CircleCollider) {
            CircleCollider cc = (CircleCollider) other;
            PVector dir = PVector.sub(cc.getPos(), this.getPos()).normalize();
            PVector point1 = PVector.add(this.getPos(), PVector.mult(dir, radius));
            PVector point2 = PVector.sub(cc.getPos(), PVector.mult(dir, cc.radius));
            float depth = point1.dist(point2);
            getNode().setPos(getNode().getPos().add(PVector.mult(dir, -depth)));
        }
    }

    @Override
    protected boolean doCollide(Collider other) {
        if (other instanceof CircleCollider) {
            CircleCollider cc = (CircleCollider) other;
            return PVector.dist(this.getPos(), cc.getPos()) < (radius + cc.radius);
        }
        return false;
    }

    public float getRadius() {
        return radius;
    }
}
