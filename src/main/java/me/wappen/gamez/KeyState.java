package me.wappen.gamez;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public enum KeyState {
    UP(),
    DOWN(),
    RELEASED(KeyState.UP),
    PRESSED(KeyState.DOWN);

    private KeyState next;

    KeyState() {
        this.next = this;
    }

    KeyState(KeyState next) {
        this.next = next;
    }

    public KeyState getNext() {
        return next;
    }
}
