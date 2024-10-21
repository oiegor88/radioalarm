package io.radioalarm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class ScheduledPlaybackTask implements Runnable {

    private final ScheduledPlaybackInfo playbackInfo;
    private final MediaPlayerController mediaPlayerController;

    @Override
    public void run() {
        log.info("Playback {} started", playbackInfo.name());

        mediaPlayerController.play(playbackInfo.source().toString());
        sleep(playbackInfo.duration());
        mediaPlayerController.stop();

        log.info("Playback {} stopped", playbackInfo.name());
    }

    private void sleep(Duration duration) {
        try {
            TimeUnit.SECONDS.sleep(duration.getSeconds());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
