package io.radioalarm.scheduler;

import jakarta.annotation.PreDestroy;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class PlaybackTaskRegistry {

  private final Map<UUID, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();

  @PreDestroy
  public void destroy() {
    tasks.values().forEach(task -> task.cancel(true));
  }

  public void register(UUID key, ScheduledFuture<?> playbackFuture) {
    tasks.put(key, playbackFuture);
  }

  public Optional<ScheduledFuture<?>> getTask(UUID key) {
    return Optional.ofNullable(tasks.get(key));
  }
}
