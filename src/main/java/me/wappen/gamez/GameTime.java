package me.wappen.gamez;

import java.time.Duration;
import java.time.Instant;

/**
 * @author LenzK
 * @since 26.06.2021
 */

public class GameTime {
    private static final float MAX_DELTA = 0.1f;
    private final Instant startTime;
    private Instant frameStartTime;
    private float deltaTime;

    public GameTime() {
        startTime = Instant.now();
        frameStartTime = Instant.now();
        deltaTime = 0;
    }

    public void update() {
        Duration dur = Duration.between(frameStartTime, Instant.now());
        deltaTime = Float.min(dur.toMillis() / 1_000f, MAX_DELTA);
        frameStartTime = Instant.now();
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getFrameStartTime() {
        return frameStartTime;
    }

    public float getDeltaTime() {
        return deltaTime;
    }
}
