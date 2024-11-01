package io.radioalarm.data;

import lombok.Data;
import lombok.experimental.Accessors;
import org.dizitart.no2.repository.annotations.Entity;
import org.dizitart.no2.repository.annotations.Id;
import org.dizitart.no2.repository.annotations.Index;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Index(fields = "refId")
@Entity(value = "playback")
public class PlaybackEntity {

  @Id
  private UUID id;

  private Boolean enabled;

  private String name;

  private String cron;

  private URL source;

  private Duration duration;
}
