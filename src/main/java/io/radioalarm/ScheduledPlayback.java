package io.radioalarm;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class ScheduledPlayback implements Runnable {

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

    @SneakyThrows
    private void sleep(Duration duration) {
        TimeUnit.SECONDS.sleep(duration.getSeconds());
    }
}
