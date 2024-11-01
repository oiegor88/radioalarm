package io.radioalarm.player;

import io.radioalarm.domain.Playback;
import jakarta.annotation.PreDestroy;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
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

  @PreDestroy
  public void destroy() {
    playbackTaskRegistry.getTasks()
        .forEach(task -> task.cancel(true));
  }

  // TODO: run on app startup
  public void schedule(Playback playback) {
    plan(playback, new CronTrigger(playback.getCron()));
  }

  public void initiate(Playback playback) {
    plan(playback, triggerContext -> Instant.now());
  }

  private void plan(Playback playback, Trigger trigger) {
    log.info("Planning playback {}", playback.getName());
    cancel(playback);
    var playbackTask = new PlaybackTask(playback, mediaPlayerController);
    var scheduledPlayback = taskScheduler.schedule(playbackTask, trigger);
    playbackTaskRegistry.register(playback.getRefId(), scheduledPlayback);
  }

  public void cancel(Playback playback) {
    playbackTaskRegistry.getTask(playback.getRefId())
        .ifPresent(task -> task.cancel(true));
  }
}
