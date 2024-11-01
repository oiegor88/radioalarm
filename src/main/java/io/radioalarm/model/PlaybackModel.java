package io.radioalarm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlaybackModel {

  @JsonProperty(value = "ref_id")
  private UUID refId;

  @JsonProperty(value = "enabled")
  private Boolean enabled;

  @JsonProperty(value = "name")
  private String name;

  @JsonProperty(value = "cron")
  private String cron;

  @JsonProperty(value = "source")
  private URL source;

  @JsonProperty(value = "duration")
  private Duration duration;


}