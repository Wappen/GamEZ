package me.wappen.gamez.components;

import javafx.util.Pair;
import me.wappen.gamez.GameTime;
import me.wappen.gamez.components.colliders.Collider;
import processing.core.PVector;

import java.util.List;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public class KinematicBody extends Component {
    private PVector vel = new PVector();
    private float gravity = 10f;
    private float drag = 0.25f;

    @Override
    public void onPhysicsTick(GameTime time) {
        // Apply gravity
        vel.add(0, gravity * time.getDeltaTime());

        // Apply drag
        vel.mult(1 - drag * time.getDeltaTime());

        // Apply velocity
        getNode().getLocalPos().add(PVector.mult(vel, time.getDeltaTime() * 50f));
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

    public static void collide(Collider c1, Collider c2) {
        KinematicBody kb1 = c1.getEntity().getComponent(KinematicBody.class);
        KinematicBody kb2 = c2.getEntity().getComponent(KinematicBody.class);

        PVector relVel;
        if (kb1 == null && kb2 == null)
            relVel = new PVector();
        else if (kb1 == null)
            relVel = kb2.getVel();
        else if (kb2 == null)
            relVel = kb1.getVel();
        else
            relVel = PVector.sub(kb1.getVel(), kb2.getVel());

        PVector pos1 = c1.getNode().getPos().copy();
        pos1.z = 0;
        PVector pos2 = c2.getNode().getPos().copy();
        pos2.z = 0;

        PVector normal = PVector.sub(pos1, pos2).normalize();
        float sepVel = PVector.dot(relVel, normal);
        PVector sepVelVec1 = PVector.mult(normal, -sepVel);
        PVector sepVelVec2 = PVector.mult(normal, sepVel);

        System.out.println(kb1.vel.toString() + "  " + kb2.vel.toString());

        if (kb1 != null)
            kb1.vel.add(sepVelVec1);
        if (kb2 != null)
            kb2.vel.add(sepVelVec2);
    }
}
