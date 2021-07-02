package me.wappen.gamez;

import me.wappen.gamez.components.KinematicBody;
import me.wappen.gamez.components.colliders.Collider;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 02.07.2021
 */

public class Collision {
    private final KinematicBody kb1;
    private final KinematicBody kb2;
    private final Collider c1;
    private final Collider c2;
    private final PVector relVel;
    private final PVector normal;
    private final PVector sepVel1;
    private final PVector sepVel2;

    private Collision(KinematicBody kb1, KinematicBody kb2,
                      Collider c1, Collider c2,
                      PVector relVel, PVector normal,
                      PVector sepVel1, PVector sepVel2) {
        this.kb1 = kb1;
        this.kb2 = kb2;
        this.c1 = c1;
        this.c2 = c2;
        this.relVel = relVel;
        this.normal = normal;
        this.sepVel1 = sepVel1;
        this.sepVel2 = sepVel2;
    }

    public static Collision checkCollision(Collider c1, Collider c2) {
        // TODO: Add check for multiple colliders using .getComponents()

        if (Collider.doCollide(c1, c2)) {
            KinematicBody kb1 = c1.getEntity().getComponent(KinematicBody.class);
            KinematicBody kb2 = c2.getEntity().getComponent(KinematicBody.class);

            return getCollision(kb1, kb2, c1, c2);
        }
        return null;
    }

    private static Collision getCollision(KinematicBody kb1, KinematicBody kb2, Collider c1, Collider c2) {
        PVector vel1 = kb1 == null ? new PVector() : kb1.getVel();
        PVector vel2 = kb2 == null ? new PVector() : kb2.getVel();

        PVector relVel = PVector.sub(vel1, vel2);
        PVector normal = PVector.sub(c1.getPos(), c2.getPos()).normalize();

        float sepVelLength = PVector.dot(relVel, normal);
        PVector sepVel1 = PVector.mult(normal, -sepVelLength);
        PVector sepVel2 = PVector.mult(normal, sepVelLength);

        return new Collision(kb1, kb2, c1, c2, relVel, normal, sepVel1, sepVel2);
    }

    public KinematicBody getKb1() {
        return kb1;
    }

    public KinematicBody getKb2() {
        return kb2;
    }

    public Collider getC1() {
        return c1;
    }

    public Collider getC2() {
        return c2;
    }

    public PVector getRelVel() {
        return relVel;
    }

    public PVector getNormal() {
        return normal;
    }

    public PVector getSepVel1() {
        return sepVel1;
    }

    public PVector getSepVel2() {
        return sepVel2;
    }

    public Collision swapped() {
        return new Collision(kb2, kb1, c2, c1, relVel, normal, sepVel2, sepVel1);
    }
}
