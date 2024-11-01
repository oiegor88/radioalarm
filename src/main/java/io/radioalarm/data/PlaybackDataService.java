package io.radioalarm.data;

import static org.dizitart.no2.filters.FluentFilter.where;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.dizitart.no2.repository.ObjectRepository;
import org.springframework.stereotype.Repository;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PlaybackDataService {

  private final ObjectRepository<PlaybackEntity> repository;

  @SneakyThrows
  @PostConstruct
  public void init() {
    var radio1 = new PlaybackEntity();
    radio1.setRefId(UUID.randomUUID());
    radio1.setName("Radio Polska 24");
    radio1.setCron("0 0 10 * * *");
    radio1.setDuration(Duration.ofMinutes(1));
    radio1.setSource(new URL("http://mp3.polskieradio.pl:8080/"));
    radio1.setEnabled(true);
    repository.insert(radio1);

    var radio2 = new PlaybackEntity();
    radio2.setRefId(UUID.randomUUID());
    radio2.setName("Radio Caprice");
    radio2.setCron("0 10 12 * * *");
    radio2.setDuration(Duration.ofHours(1).plus(Duration.ofMinutes(30)));
    radio2.setSource(new URL("http://mp3.polskieradio.pl:9090/"));
    radio2.setEnabled(false);
    repository.insert(radio2);
  }

  public List<PlaybackEntity> findAll() {
    return repository.find().toList();
  }

  public Optional<PlaybackEntity> findOne(UUID refId) {
    return Optional.ofNullable(
        repository.find(where("refId").eq(refId)).firstOrNull()
    );
  }

  public PlaybackEntity save(PlaybackEntity model) {
    model.setRefId(UUID.randomUUID());
    repository.insert(model);
    return model;
  }

  public UUID delete(UUID refId) {
    repository.remove(where("refId").eq(refId));
    return refId;
  }
}
