package me.wappen.gamez.components;

import me.wappen.gamez.GameTime;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public class KinematicBody extends Component {
    private PVector vel = new PVector();
    private float gravity = 10f;
    private float drag = 0.25f;

    @Override
    public void onTick(GameTime time) {
        // Apply gravity
        vel.add(0, gravity * time.getDeltaTime());

        // Apply drag
        vel.mult(1 - drag * time.getDeltaTime());

        // Apply velocity
        getNode().getLocalPos().add(PVector.mult(vel, time.getDeltaTime() * 60f, null));
    }

    public PVector getVel() {
        return vel;
    }

    public void setVel(PVector vel) {
        this.vel = vel;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getDrag() {
        return drag;
    }

    public void setDrag(float drag) {
        this.drag = drag;
    }
}
