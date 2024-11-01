package io.radioalarm.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RadioAlarmException extends RuntimeException {

  private final HttpStatus status;

  public RadioAlarmException(String message, HttpStatus status, Throwable cause) {
    super(message, cause);
    this.status = status;
  }

  public RadioAlarmException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
