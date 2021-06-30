package me.wappen.gamez;

import me.wappen.gamez.components.KinematicBody;
import me.wappen.gamez.components.colliders.Collider;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.*;

/**
 * @author LenzK
 * @since 26.06.2021
 */

public class Game {
    private final GameTime time = new GameTime();
    private final GameTime physicsTime = new GameTime();
    private final Timer physicsTimer = new Timer();
    private final KeyState[] keyStates = new KeyState[256];
    private Node root;
    private View view;

    private List<Collider> colliders = new ArrayList<>();

    public void run(int w, int h) {
        Arrays.fill(keyStates, KeyState.UP);
        root = new Node(new Entity("root"));
        view = new View(new PVector(), new PVector(w, h));

        Window.setGame(this);
        Window.setRes(w, h);
        PApplet.main(Window.class);
    }

    public void init() {
        physicsTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                physicsTick();
            }
        }, 20, 20);
    }

    public void start() {}

    public void tick() {
        time.update();
        root.tick(time);

        // Update keyboard state
        for (int i = 0; i < keyStates.length; i++) {
            keyStates[i] = keyStates[i].getNext();
        }
    }

    public void physicsTick() {
        physicsTime.update();

        // Collision checks
        for (int i = 0; i < colliders.size(); i++) {
            for (int j = i + 1; j < colliders.size(); j++) {
                Collider c1 = colliders.get(i);
                Collider c2 = colliders.get(j);

                if (Collider.checkCollision(c1, c2)) {
                    KinematicBody.collide(c1, c2);

                    c1.getEntity().collide(c2);
                    c2.getEntity().collide(c1);
                }
            }
        }

        root.physicsTick(physicsTime);
    }

    public void addCollider(Collider collider) {
        colliders.add(collider);
    }

    public void removeCollider(Collider collider) {
        colliders.remove(collider);
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
