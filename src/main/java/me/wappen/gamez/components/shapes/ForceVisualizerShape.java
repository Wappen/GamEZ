package me.wappen.gamez.components.shapes;

import me.wappen.gamez.GameTime;
import me.wappen.gamez.Window;
import me.wappen.gamez.components.KinematicBody;
import processing.core.PShape;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 02.07.2021
 */

public class ForceVisualizerShape extends Shape {
    private KinematicBody kb;
    private float dt;

    @Override
    public void onSpawn() {
        kb = getEntity().getComponent(KinematicBody.class);
    }

    @Override
    public void onPhysicsTick(GameTime time) {
        dt = time.getDeltaTime();
    }

    @Override
    public PShape getShape() {
        PShape shape = Window.getInstance().createShape(PShape.GROUP);

        PVector vel = PVector.div(kb.getVel(), dt * 4);
        PShape velShape1 = Window.getInstance().createShape(PShape.LINE, 0, 0, vel.x, vel.y);
        PShape velShape2 = Window.getInstance().createShape(PShape.LINE, 0, 0, vel.x, vel.y);
        PShape gravShape1 = Window.getInstance().createShape(PShape.LINE, 0, 0, 0, kb.getGravity() * 4);
        PShape gravShape2 = Window.getInstance().createShape(PShape.LINE, 0, 0, 0, kb.getGravity() * 4);

        velShape1.setStroke(0x88_0fdd0f);
        velShape1.setStrokeWeight(4);
        velShape2.setStroke(0xff_00aa00);
        velShape2.setStrokeWeight(1);
        gravShape1.setStroke(0x88_0f0fdd);
        gravShape1.setStrokeWeight(4);
        gravShape2.setStroke(0xff_0000aa);
        gravShape2.setStrokeWeight(1);

        shape.addChild(velShape1);
        shape.addChild(velShape2);
        shape.addChild(gravShape1);
        shape.addChild(gravShape2);

        return shape;
    }
}
