package io.radioalarm.player;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

@Configuration(proxyBeanMethods = false)
public class MediaPlayerConfiguration {

  public static final int MAX_TASKS = 5;

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

  @Bean
  public ThreadPoolTaskScheduler taskScheduler() {
    var taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setThreadNamePrefix("playback");
    taskScheduler.setPoolSize(MAX_TASKS);
    taskScheduler.initialize();
    return taskScheduler;
  }
}
