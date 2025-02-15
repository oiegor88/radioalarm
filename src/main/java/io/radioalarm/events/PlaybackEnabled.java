package io.radioalarm.events;

import io.radioalarm.domain.Playback;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlaybackEnabled extends ApplicationEvent {

  private final Playback playback;

  public PlaybackEnabled(Object source, Playback playback) {
    super(source);
    this.playback = playback;
  }
}
