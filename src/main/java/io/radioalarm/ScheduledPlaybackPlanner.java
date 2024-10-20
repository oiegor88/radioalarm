package io.radioalarm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledPlaybackPlanner implements ApplicationRunner {

    private final RadioPlaybackProperties playbackProperties;
    private final MediaPlayerController mediaPlayerController;
    private final TaskScheduler taskScheduler;


    public void run(ApplicationArguments args) {
        playbackProperties.scheduled().stream()
                .filter(ScheduledPlaybackInfo::enabled)
                .forEach(info -> {
                    log.info("Planning playback {}", info);
                    taskScheduler.schedule(
                            new ScheduledPlayback(info, mediaPlayerController),
                            new CronTrigger(info.cron())
                    );
                });
    }
}
