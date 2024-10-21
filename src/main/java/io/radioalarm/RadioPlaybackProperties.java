package io.radioalarm;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.LinkedHashSet;

@Validated
@ConfigurationProperties(prefix = "radio.playback")
public record RadioPlaybackProperties (
        @Valid
        @NotEmpty
        @Size(max = RadioAlarmApplication.MAX_TASKS)
        LinkedHashSet<ScheduledPlaybackInfo> scheduled
) {}
