package io.radioalarm.events;

import io.radioalarm.scheduler.PlaybackTaskManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaybackEventListener {

  private final PlaybackTaskManager playbackTaskManager;

  @EventListener
  public void onPlaybackCreated(PlaybackCreated event) {
    playbackTaskManager.schedule(event.getPlayback());
  }

  @EventListener
  public void onPlaybackUpdated(PlaybackUpdated event) {
    playbackTaskManager.schedule(event.getPlayback());
  }

  @EventListener
  public void onPlaybackDeleted(PlaybackDeleted event) {
    playbackTaskManager.cancel(event.getId());
  }

  @EventListener
  public void onPlaybackDisabled(PlaybackDisabled event) {
    playbackTaskManager.cancel(event.getPlayback());
  }

  @EventListener
  public void onPlaybackDeleted(PlaybackEnabled event) {
    playbackTaskManager.schedule(event.getPlayback());
  }

}
