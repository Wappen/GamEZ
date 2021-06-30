package me.wappen.gamez.components.colliders;

import me.wappen.gamez.Rect;
import me.wappen.gamez.components.Component;

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

    public static boolean checkCollision(Collider c1, Collider c2) {
        if (Rect.intersect(c1.getBounds().offset(c1.getNode().getPos()), c2.getBounds().offset(c2.getNode().getPos()))) {
            return c1.checkCollision(c2);
        }
        return false;
    }

    protected abstract boolean checkCollision(Collider other);
}
