package io.radioalarm.player;

import io.radioalarm.domain.Playback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlaybackTaskManager {

  private final MediaPlayerController mediaPlayerController;
  private final ThreadPoolTaskScheduler taskScheduler;

  public void schedule(Playback playback) {
    log.info("Planning playback {}", playback);
    var playbackTask = new PlaybackTask(playback, mediaPlayerController);
    var playbackTrigger = new CronTrigger(playback.getCron());
    taskScheduler.schedule(playbackTask, playbackTrigger);
  }
}
