package io.radioalarm;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@ConfigurationProperties(prefix = "radio.playback")
public record RadioPlaybackProperties (
        @Valid
        @NotEmpty
        List<ScheduledPlaybackInfo> scheduled
) {}
