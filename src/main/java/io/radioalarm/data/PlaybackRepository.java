package io.radioalarm.data;

import static org.dizitart.no2.filters.FluentFilter.where;

import lombok.RequiredArgsConstructor;
import org.dizitart.no2.filters.FluentFilter;
import org.dizitart.no2.repository.ObjectRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PlaybackRepository {

  private static final FluentFilter ID = where(PlaybackEntity.FIELD_ID);

  private final ObjectRepository<PlaybackEntity> nitriteRepository;

  public List<PlaybackEntity> findAll() {
    return nitriteRepository.find().toList();
  }

  public Optional<PlaybackEntity> findOne(UUID id) {
    return Optional.ofNullable(
        nitriteRepository.find(ID.eq(id)).firstOrNull()
    );
  }

  public PlaybackEntity save(PlaybackEntity model) {
    if (model.getId() == null) {
      model.setId(UUID.randomUUID());
      nitriteRepository.insert(model);
    } else {
      nitriteRepository.update(ID.eq(model.getId()), model);
    }
    return model;
  }

  public UUID delete(UUID id) {
    nitriteRepository.remove(ID.eq(id));
    return id;
  }
}
