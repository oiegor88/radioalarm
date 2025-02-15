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
@Entity(PlaybackEntity.TABLE_NAME)
@Index(fields = PlaybackEntity.FIELD_ID)
public class PlaybackEntity {

  public static final String TABLE_NAME = "playback";
  public static final String FIELD_ID = "id";

  @Id
  private UUID id;

  private Boolean enabled;

  private String name;

  private String cron;

  private URL source;

  private Duration duration;
}
