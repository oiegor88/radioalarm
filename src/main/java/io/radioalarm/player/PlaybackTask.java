package io.radioalarm.player;

import io.radioalarm.domain.Playback;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PlaybackTask implements Runnable {

  private final Playback playbackInfo;
  private final MediaPlayerController mediaPlayerController;

  @Override
  public void run() {
    log.info("Playback {} started", playbackInfo.getName());

    mediaPlayerController.play(playbackInfo.getSource().toString());
    sleep(playbackInfo.getDuration());
    mediaPlayerController.stop();

    log.info("Playback {} stopped", playbackInfo.getName());
  }

  private void sleep(Duration duration) {
    try {
      TimeUnit.SECONDS.sleep(duration.getSeconds());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
