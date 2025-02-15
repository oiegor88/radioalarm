package io.radioalarm.events;

import io.radioalarm.domain.Playback;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlaybackCreated extends ApplicationEvent {

  private final Playback playback;

  public PlaybackCreated(Object source, Playback playback) {
    super(source);
    this.playback = playback;
  }
}
