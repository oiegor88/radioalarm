package io.radioalarm.scheduler;

import io.radioalarm.domain.Playback;
import io.radioalarm.service.PlaybackService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupPlaybackScheduler implements ApplicationRunner {

  private final PlaybackTaskManager playbackTaskManager;
  private final PlaybackService playbackService;

  @Override
  public void run(ApplicationArguments args) {
    playbackService.getPlaybacks().stream()
        .filter(Playback::getEnabled)
        .forEach(playbackTaskManager::schedule);
  }
}
