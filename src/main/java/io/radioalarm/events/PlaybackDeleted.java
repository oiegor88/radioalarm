package io.radioalarm.events;

import java.util.UUID;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlaybackDeleted extends ApplicationEvent {

  private final UUID id;

  public PlaybackDeleted(Object source, UUID id) {
    super(source);
    this.id = id;
  }
}
