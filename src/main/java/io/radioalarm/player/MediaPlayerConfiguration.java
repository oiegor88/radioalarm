package io.radioalarm.player;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

@Configuration(proxyBeanMethods = false)
public class MediaPlayerConfiguration {

  @Bean
  public AudioPlayerComponent audioPlayerComponent() {
    return new AudioPlayerComponent();
  }

  @Bean
  public MediaPlayer mediaPlayer(AudioPlayerComponent audioPlayerComponent) {
    return audioPlayerComponent.mediaPlayer();
  }

  @Bean
  public MediaPlayerController mediaPlayerController(MediaPlayer mediaPlayer) {
    return new MediaPlayerController(mediaPlayer);
  }
}
