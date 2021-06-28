package me.wappen.gamez;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author LenzK
 * @since 26.06.2021
 */

public class GameTime {
    private final LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime frameStartTime = LocalDateTime.now();
    private float deltaTime = 1f;

    public void update() {
        Duration dur = Duration.between(LocalDateTime.now(), frameStartTime);
        deltaTime = 1_000_000_000f / dur.getNano() / 60f;
        frameStartTime = LocalDateTime.now();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getFrameStartTime() {
        return frameStartTime;
    }

    public float getDeltaTime() {
        return deltaTime;
    }
}
