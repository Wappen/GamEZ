package me.wappen.gamez;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LenzK
 * @since 26.06.2021
 */

public class Game {
    private final GameTime time = new GameTime();
    private final KeyState[] keyStates = new KeyState[256];
    private Node root;
    private View view;

    public void run(int w, int h) {
        Arrays.fill(keyStates, KeyState.UP);
        root = new Node(new Entity("root"));
        view = new View(new PVector(), new PVector(w, h));

        Window.setGame(this);
        Window.setRes(w, h);
        PApplet.main(Window.class);
    }

    public void start() {}

    public void tick() {
        root.tick(time);

        for (int i = 0; i < keyStates.length; i++) {
            keyStates[i] = keyStates[i].getNext();
        }

        time.update();
    }

    public Node spawn(Entity entity) {
        Node node = new Node(entity);
        node.spawn(this, root);
        return node;
    }

    public Node spawn(Entity entity, Node parent) {
        Node node = new Node(entity);
        node.spawn(this, parent);
        return node;
    }

    public Entity findEntity(String name) {
        return root.firstChild(n -> n.getEntity().getName().equals(name)).getEntity();
    }

    public List<Node> getAllNodes() {
        List<Node> nodes = new ArrayList<>();
        root.addChildren(nodes);
        return nodes;
    }

    public KeyState getKeyState(int key) {
        if (key >= 0 && key < keyStates.length)
            return keyStates[key];
        return KeyState.UP;
    }

    public void keyDown(int key) {
        if (key >= 0 && key < keyStates.length) {
            if (keyStates[key] != KeyState.DOWN)
                keyStates[key] = KeyState.PRESSED;
        }
    }

    public void keyUp(int key) {
        if (key >= 0 && key < keyStates.length)
            if (keyStates[key] != KeyState.UP)
                keyStates[key] = KeyState.RELEASED;
    }

    public GameTime getTime() {
        return time;
    }

    public Node getRoot() {
        return root;
    }

    public View getView() {
        return view;
    }
}
