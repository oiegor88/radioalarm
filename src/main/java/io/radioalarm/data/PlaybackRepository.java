package io.radioalarm.data;

import static org.dizitart.no2.filters.FluentFilter.where;

import lombok.RequiredArgsConstructor;
import org.dizitart.no2.repository.ObjectRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PlaybackRepository {

  private final ObjectRepository<PlaybackEntity> nitriteRepository;

  public List<PlaybackEntity> findAll() {
    return nitriteRepository.find().toList();
  }

  public Optional<PlaybackEntity> findOne(UUID refId) {
    return Optional.ofNullable(
        nitriteRepository.find(where("id").eq(refId)).firstOrNull()
    );
  }

  public PlaybackEntity save(PlaybackEntity model) {
    model.setId(UUID.randomUUID());
    nitriteRepository.insert(model);
    return model;
  }

  public UUID delete(UUID id) {
    nitriteRepository.remove(where("id").eq(id));
    return id;
  }
}
