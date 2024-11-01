package io.radioalarm.service;

import io.radioalarm.api.PlaybackCreateRequest;
import io.radioalarm.api.PlaybackUpdateRequest;
import io.radioalarm.data.PlaybackRepository;
import io.radioalarm.domain.Playback;
import io.radioalarm.exception.PlaybackNotFound;
import io.radioalarm.mapper.PlaybackMapper;
import io.radioalarm.player.PlaybackTaskManager;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaybackService {

  private final PlaybackTaskManager playbackTaskManager;
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
    var model = new Playback()
        .setName(request.getName())
        .setCron(request.getCron())
        .setDuration(request.getDuration())
        .setSource(request.getSource())
        .setEnabled(true);


    playbackTaskManager.schedule(model);

    return playbackMapper.fromEntity(
        playbackRepository.save(
            playbackMapper.toEntity(model)
        )
    );
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
        .orElseThrow(() -> new PlaybackNotFound(refId));

    return playbackMapper.fromEntity(
        playbackRepository.save(playback)
    );
  }

  public UUID deletePlayback(@NonNull UUID refId) {
    return playbackRepository.delete(refId);
  }

  public void disablePlayback(@NonNull UUID refId) {
    var playback = playbackRepository.findOne(refId)
        .map(existing -> existing.setEnabled(false))
        .orElseThrow(() -> new PlaybackNotFound(refId));
    playbackRepository.save(playback);
  }

  public void enablePlayback(@NonNull UUID refId) {
    var playback = playbackRepository.findOne(refId)
        .map(existing -> existing.setEnabled(true))
        .orElseThrow(() -> new PlaybackNotFound(refId));
    playbackRepository.save(playback);
  }

  public void startPlayback(@NonNull UUID refId) {
    throw new UnsupportedOperationException();
  }

  public void stopPlayback(@NonNull UUID refId) {
    throw new UnsupportedOperationException();
  }
}
