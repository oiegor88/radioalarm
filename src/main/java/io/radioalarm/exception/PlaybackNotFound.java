package io.radioalarm.exception;

import java.util.UUID;
import org.springframework.http.HttpStatus;

public final class PlaybackNotFound extends RadioAlarmException {
  public PlaybackNotFound(UUID refId) {
    super("Playback %s not found".formatted(refId), HttpStatus.NOT_FOUND);
  }
}
