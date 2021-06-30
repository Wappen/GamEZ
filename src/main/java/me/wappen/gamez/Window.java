package me.wappen.gamez;

import processing.core.PApplet;
import processing.core.PShape;
import processing.event.KeyEvent;

import java.util.List;

/**
 * @author LenzK
 * @since 26.06.2021
 */

public class Window extends PApplet {
    private static Game game;

    private static Window instance;
    private static int resX;
    private static int resY;

    public static Window getInstance() {
        return instance;
    }

    public Window() {
        instance = this;
    }

    @Override
    public void settings() {
        size(resX, resY);
    }

    @Override
    public void setup() {
        game.start();
        frameRate(60);
        game.init();
    }

    @Override
    public void draw() {
        game.tick();
        game.getView().apply(this);

        List<Node> nodes = game.getAllNodes();
        nodes.sort((a, b) -> Float.compare(a.getPos().z, b.getPos().z));
        for (Node node : nodes) {
            List<PShape> shapes = node.getEntity().getShapes();
            if (!shapes.isEmpty()) {
                for (PShape shape : shapes) {
                    shape(shape, node.getPos().x, node.getPos().y);
                }
            }
        }

        game.getView().revert(this);

        fill(0);
        textAlign(LEFT, TOP);
        text(frameRate, 5, 5);
        text(game.getTime().getDeltaTime(), 5, 15);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        game.keyDown(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        game.keyUp(event.getKeyCode());
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        Window.game = game;
    }

    public static void setRes(int resX, int resY) {
        Window.resX = resX;
        Window.resY = resY;
    }

    public static int getResX() {
        return resX;
    }

    public static int getResY() {
        return resY;
    }
}
