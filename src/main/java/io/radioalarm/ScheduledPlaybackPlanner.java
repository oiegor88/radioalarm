package io.radioalarm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledPlaybackPlanner implements ApplicationRunner {

    private final RadioPlaybackProperties playbackProperties;
    private final MediaPlayerController mediaPlayerController;
    private final ThreadPoolTaskScheduler taskScheduler;

    public void run(ApplicationArguments args) {
        playbackProperties.scheduled().stream()
                .filter(ScheduledPlaybackInfo::enabled)
                .forEach(this::planTask);
    }

    private void planTask(ScheduledPlaybackInfo playbackInfo) {
        log.info("Planning playback {}", playbackInfo);
        var playbackTask = new ScheduledPlaybackTask(playbackInfo, mediaPlayerController);
        var playbackTrigger = new CronTrigger(playbackInfo.cron());
        taskScheduler.schedule(playbackTask, playbackTrigger);
    }
}
