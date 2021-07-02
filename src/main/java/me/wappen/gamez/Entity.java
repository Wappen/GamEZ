package me.wappen.gamez;

import me.wappen.gamez.components.Component;
import me.wappen.gamez.components.shapes.Shape;
import processing.core.PShape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LenzK
 * @since 26.06.2021
 */

public class Entity {
    private String name;
    private Game game;
    private Node node;
    private final List<Component> components;

    public Entity(String name) {
        this(name, new ArrayList<>());
    }

    public Entity(String name, Component... components) {
        this(name, Arrays.asList(components));
    }

    public Entity(String name, List<Component> components) {
        this.name = name;
        this.components = new ArrayList<>();
        addComponents(components);
    }

    public void addComponent(Component component) {
        component.addedToEntity(this);
        components.add(component);
    }

    public void addComponents(List<Component> components) {
        for (Component component : components) {
            component.addedToEntity(this);
            this.components.add(component);
        }
    }

    public <T extends Component> T getComponent(Class<T> cls) {
        for (Component comp : components) {
            if (comp.getClass() == cls)
                return (T) comp;
        }

        return null;
    }

    public <T extends Component> List<T> getComponents(Class<T> cls) {
        List<T> comps = new ArrayList<>();

        for (Component comp : components) {
            if (comp.getClass() == cls)
                comps.add((T) comp);
        }

        return comps;
    }

    public void tick(GameTime time) {
        for (Component comp : components) {
            comp.onTick(time);
        }
    }

    public void physicsTick(GameTime time) {
        for (Component comp : components) {
            comp.onPhysicsTick(time);
        }
    }

    public void spawn(Node node) {
        this.game = node.getGame();
        this.node = node;

        for (Component comp : components) {
            comp.spawn(node);
            comp.onSpawn();
        }
    }

    public void collide(Collision collision) {
        for (Component comp : components) {
            comp.onCollide(collision);
        }
    }

    public void deleteComponents() {
        for (Component comp : components) {
            comp.onDelete();
        }
    }

    public Node getNode() {
        return node;
    }

    public List<PShape> getShapes() {
        List<PShape> shapes = new ArrayList<>();

        for (Component comp : components) {
            if (comp instanceof Shape) {
                PShape sh = ((Shape)comp).getShape();
                shapes.add(sh);
            }
        }

        return shapes;
    }

    public Game getGame() {
        return game;
    }

    public String getName() {
        return name;
    }
}
