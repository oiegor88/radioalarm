package io.radioalarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

@SpringBootApplication
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(RadioPlaybackProperties.class)
public class RadioAlarmApplication {

    public static final int MAX_TASKS = 5;

    public static void main(String[] args) {
        SpringApplication.run(RadioAlarmApplication.class, args);
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        var taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setThreadNamePrefix("playback");
        taskScheduler.setPoolSize(MAX_TASKS);
        taskScheduler.initialize();
        return taskScheduler;
    }

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
