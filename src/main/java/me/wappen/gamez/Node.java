package me.wappen.gamez;

import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author LenzK
 * @since 26.06.2021
 */

public class Node {
    private final Entity entity;
    private Game game;
    private Node parent;
    private final List<Node> children;
    private PVector localPos;

    public Node(Entity entity) {
        this.entity = entity;
        children = new ArrayList<>();
        localPos = new PVector();
    }

    public void tick(GameTime time) {
        entity.tick(time);
        for (Node child : children)
            child.tick(time);
    }

    public void spawn(Game game, Node parent) {
        this.game = game;
        this.parent = parent;
        parent.getChildren().add(this);
        entity.spawn(this);
    }

    public void delete() {
        while (!children.isEmpty())
            children.get(0).delete();
        if (parent != null)
            parent.getChildren().remove(this);
        entity.deleteComponents();
    }

    public void addChildren(List<Node> list) {
        for (Node child : children) {
            list.add(child);
            child.addChildren(list);
        }
    }

    public Node firstChild(Predicate<Node> predicate) {
        for (Node child : children) {
            if (predicate.test(child))
                return child;

            Node node = child.firstChild(predicate);
            if (node != null)
                return node;
        }

        return null;
    }

    public void forEachChild(Consumer<Node> consumer) {
        for (Node child : children) {
            consumer.accept(child);
            child.forEachChild(consumer);
        }
    }

    public Entity getEntity() {
        return entity;
    }

    public Game getGame() {
        return game;
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public PVector getPos() {
        if (parent != null)
            return parent.getPos().add(localPos);
        return localPos.copy();
    }

    public void setPos(PVector pos) {
        if (parent != null)
            PVector.sub(pos, parent.getPos(), localPos);
        else
            localPos.set(pos);
    }

    public PVector getLocalPos() {
        return localPos;
    }

    public void setLocalPos(PVector pos) {
        this.localPos = pos;
    }
}
