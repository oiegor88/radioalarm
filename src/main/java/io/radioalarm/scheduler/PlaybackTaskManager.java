package io.radioalarm.scheduler;

import io.radioalarm.domain.Playback;
import io.radioalarm.player.MediaPlayerController;
import io.radioalarm.player.PlaybackTask;
import java.util.UUID;
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
  private final PlaybackTaskRegistry playbackTaskRegistry;
  private final ThreadPoolTaskScheduler taskScheduler;

  public void schedule(Playback playback) {
    log.info("Scheduling playback {}", playback);
    tryCancel(playback);
    var trigger = new CronTrigger(playback.getCron());
    var playbackTask = new PlaybackTask(playback, mediaPlayerController);
    var scheduledPlayback = taskScheduler.schedule(playbackTask, trigger);
    playbackTaskRegistry.register(playback.getId(), scheduledPlayback);
  }

  public void tryCancel(Playback playback) {
    tryCancel(playback.getId());
  }

  public void tryCancel(UUID playbackId) {
    playbackTaskRegistry.getTask(playbackId)
        .ifPresent(task -> task.cancel(true));
  }
}
