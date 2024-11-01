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

  // TODO: fetch all enabled and schedule at startup
  public void schedule(Playback playback) {
    log.info("Scheduling playback {}", playback.getName());
    cancel(playback);
    var trigger = new CronTrigger(playback.getCron());
    var playbackTask = new PlaybackTask(playback, mediaPlayerController);
    var scheduledPlayback = taskScheduler.schedule(playbackTask, trigger);
    playbackTaskRegistry.register(playback.getId(), scheduledPlayback);
  }

  public void cancel(Playback playback) {
    cancel(playback.getId());
  }

  public void cancel(UUID playbackId) {
    playbackTaskRegistry.getTask(playbackId)
        .ifPresent(task -> task.cancel(true));
  }
}
