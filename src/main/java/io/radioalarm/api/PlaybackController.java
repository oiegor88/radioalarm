package io.radioalarm.api;

import io.radioalarm.domain.Playback;
import io.radioalarm.service.PlaybackService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playback")
@RequiredArgsConstructor
public class PlaybackController {

  private final PlaybackService playbackService;

  @GetMapping
  public List<Playback> getPlaybacks() {
    return playbackService.getPlaybacks();
  }

  @GetMapping("/{ref_id}")
  public Playback getPlayback(@PathVariable(name = "ref_id") UUID refId) {
    return playbackService.getPlayback(refId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Playback createPlayback(@RequestBody PlaybackCreateRequest request) {
    return playbackService.createPlayback(request);
  }

  @PutMapping("/{ref_id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Playback updatePlayback(@PathVariable(name = "ref_id") UUID refId,
                                 @RequestBody PlaybackUpdateRequest request) {
    return playbackService.updatePlayback(refId, request);
  }

  @DeleteMapping("/{ref_id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public UUID deletePlayback(@PathVariable(name = "ref_id") UUID refId) {
    return playbackService.deletePlayback(refId);
  }

  @PatchMapping("/{ref_id}/disable")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void disablePlayback(@PathVariable(name = "ref_id") UUID refId) {
    playbackService.disablePlayback(refId);
  }

  @PatchMapping("/{ref_id}/enable")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void enablePlayback(@PathVariable(name = "ref_id") UUID refId) {
    playbackService.enablePlayback(refId);
  }

  @PatchMapping("/{ref_id}/start")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void startPlayback(@PathVariable(name = "ref_id") UUID refId) {
    playbackService.startPlayback(refId);
  }

  @PatchMapping("/{ref_id}/stop")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void stopPlayback(@PathVariable(name = "ref_id") UUID refId) {
    playbackService.stopPlayback(refId);
  }
}
