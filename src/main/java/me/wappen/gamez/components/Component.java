package me.wappen.gamez.components;

import me.wappen.gamez.*;

/**
 * @author LenzK
 * @since 26.06.2021
 */

public abstract class Component {
    private Game game;
    private Node node;
    private Entity entity;

    public void addedToEntity(Entity entity) {
        this.entity = entity;
    }

    public void spawn(Node node) {
        this.node = node;
        game = node.getGame();
    }

    public void onTick(GameTime time) {}
    public void onPhysicsTick(GameTime time) {}
    public void onSpawn() {}
    public void onDelete() {}
    public void onCollide(Collision collision) {}

    public Game getGame() {
        return game;
    }

    public Node getNode() {
        return node;
    }

    public Entity getEntity() {
        return entity;
    }
}
