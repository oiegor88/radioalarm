package io.radioalarm.service;

import io.radioalarm.api.PlaybackCreateRequest;
import io.radioalarm.api.PlaybackUpdateRequest;
import io.radioalarm.data.PlaybackRepository;
import io.radioalarm.domain.Playback;
import io.radioalarm.events.PlaybackCreated;
import io.radioalarm.events.PlaybackDeleted;
import io.radioalarm.events.PlaybackDisabled;
import io.radioalarm.events.PlaybackEnabled;
import io.radioalarm.events.PlaybackUpdated;
import io.radioalarm.exception.PlaybackNotFound;
import io.radioalarm.mapper.PlaybackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaybackService {
  private final ApplicationEventPublisher eventPublisher;
  private final PlaybackRepository playbackRepository;
  private final PlaybackMapper playbackMapper;

  public List<Playback> getPlaybacks() {
    return playbackRepository.findAll().stream()
        .map(playbackMapper::fromEntity)
        .toList();
  }

  public Playback getPlayback(@NonNull UUID refId) {
    return playbackRepository.findOne(refId)
        .map(playbackMapper::fromEntity)
        .orElseThrow(() -> new PlaybackNotFound(refId));
  }

  public Playback createPlayback(@NonNull PlaybackCreateRequest request) {
    var playback = playbackMapper.fromEntity(
        playbackRepository.save(
            playbackMapper.toEntity(new Playback()
                .setName(request.getName())
                .setCron(request.getCron())
                .setDuration(request.getDuration())
                .setSource(request.getSource())
                .setEnabled(true)
            )
        )
    );
    eventPublisher.publishEvent(new PlaybackCreated(this, playback));
    return playback;
  }

  public Playback updatePlayback(@NonNull UUID refId,
                                 @NonNull PlaybackUpdateRequest request) {
    var playback = playbackRepository.findOne(refId)
        .map(existing -> existing
            .setName(request.getName())
            .setCron(request.getCron())
            .setDuration(request.getDuration())
            .setSource(request.getSource())
        )
        .map(playbackRepository::save)
        .map(playbackMapper::fromEntity)
        .orElseThrow(() -> new PlaybackNotFound(refId));

    eventPublisher.publishEvent(new PlaybackUpdated(this, playback));
    return playback;
  }

  public UUID deletePlayback(@NonNull UUID id) {
    var playbackId = playbackRepository.delete(id);

    eventPublisher.publishEvent(new PlaybackDeleted(this, playbackId));
    return playbackId;
  }

  public void disablePlayback(@NonNull UUID refId) {
    var playback = playbackRepository.findOne(refId)
        .map(existing -> existing.setEnabled(false))
        .map(playbackRepository::save)
        .map(playbackMapper::fromEntity)
        .orElseThrow(() -> new PlaybackNotFound(refId));

    eventPublisher.publishEvent(new PlaybackDisabled(this, playback));
  }

  public void enablePlayback(@NonNull UUID refId) {
    var playback = playbackRepository.findOne(refId)
        .map(existing -> existing.setEnabled(true))
        .map(playbackRepository::save)
        .map(playbackMapper::fromEntity)
        .orElseThrow(() -> new PlaybackNotFound(refId));

    eventPublisher.publishEvent(new PlaybackEnabled(this, playback));
  }
}
