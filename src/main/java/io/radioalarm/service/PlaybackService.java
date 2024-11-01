package io.radioalarm.service;

import io.radioalarm.api.PlaybackCreateRequest;
import io.radioalarm.api.PlaybackUpdateRequest;
import io.radioalarm.data.PlaybackRepository;
import io.radioalarm.exception.PlaybackNotFound;
import io.radioalarm.mapper.PlaybackMapper;
import io.radioalarm.model.PlaybackModel;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaybackService {

  private final PlaybackRepository playbackRepository;
  private final PlaybackMapper playbackMapper;

  public List<PlaybackModel> getPlaybacks() {
    return playbackRepository.findAll().stream()
        .map(playbackMapper::toModel)
        .toList();
  }

  public PlaybackModel getPlayback(@NonNull UUID refId) {
    return playbackRepository.findOne(refId)
        .map(playbackMapper::toModel)
        .orElseThrow(() -> new PlaybackNotFound(refId));
  }

  public PlaybackModel createPlayback(@NonNull PlaybackCreateRequest request) {
    var model = new PlaybackModel()
        .setName(request.getName())
        .setCron(request.getCron())
        .setDuration(request.getDuration())
        .setSource(request.getSource())
        .setEnabled(true);
    return playbackMapper.toModel(
        playbackRepository.save(
            playbackMapper.toEntity(model)
        )
    );
  }

  public PlaybackModel updatePlayback(@NonNull UUID refId,
                                      @NonNull PlaybackUpdateRequest request) {
    var playback = playbackRepository.findOne(refId)
        .map(existing -> existing
            .setName(request.getName())
            .setCron(request.getCron())
            .setDuration(request.getDuration())
            .setSource(request.getSource())
        )
        .orElseThrow(() -> new PlaybackNotFound(refId));

    return playbackMapper.toModel(
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
