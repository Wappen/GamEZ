package me.wappen.gamez.components.colliders;

import me.wappen.gamez.Rect;
import me.wappen.gamez.components.Component;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 30.06.2021
 */

public abstract class Collider extends Component {
    private Rect bounds;

    public Collider(Rect bounds) {
        this.bounds = bounds;
    }

    @Override
    public void onSpawn() {
        getGame().addCollider(this);
    }

    @Override
    public void onDelete() {
        getGame().removeCollider(this);
    }

    public Rect getBounds() {
        return bounds;
    }

    public PVector getPos() {
        return new PVector(getNode().getPos().x, getNode().getPos().y);
    }

    public static boolean doCollide(Collider c1, Collider c2) {
        if (Rect.overlap(c1.getBounds().offset(c1.getPos()), c2.getBounds().offset(c2.getPos()))) {
            return c1.doCollide(c2);
        }
        return false;
    }

    protected abstract boolean doCollide(Collider other);
}
