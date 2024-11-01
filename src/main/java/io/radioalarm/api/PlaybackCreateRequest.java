package io.radioalarm.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.net.URL;
import java.time.Duration;
import lombok.Data;

@Data
public class PlaybackCreateRequest {

  @NotBlank
  @JsonProperty(value = "name")
  private String name;

  @NotBlank
  @JsonProperty(value = "cron")
  private String cron;

  @NotNull
  @JsonProperty(value = "source")
  private URL source;

  @NotNull
  @JsonProperty(value = "duration")
  private Duration duration;
}
