package io.radioalarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

@SpringBootApplication
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(RadioPlaybackProperties.class)
public class RadioAlarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(RadioAlarmApplication.class, args);
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setThreadNamePrefix("PlaybackScheduler");
        threadPoolTaskScheduler.setPoolSize(5);
        return threadPoolTaskScheduler;
    }

    @Bean
    AudioPlayerComponent audioPlayerComponent() {
        return new AudioPlayerComponent();
    }

    @Bean
    MediaPlayer mediaPlayer(AudioPlayerComponent audioPlayerComponent) {
        return audioPlayerComponent.mediaPlayer();
    }

    @Bean(destroyMethod = "exit")
    MediaPlayerController mediaPlayerController(MediaPlayer mediaPlayer) {
        return new MediaPlayerController(mediaPlayer);
    }
}
