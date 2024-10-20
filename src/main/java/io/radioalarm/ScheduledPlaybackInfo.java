package io.radioalarm;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.validation.annotation.Validated;

import java.net.URL;
import java.time.Duration;

@Validated
public record ScheduledPlaybackInfo (
    @NotNull
    String name,

    @NotNull
    URL source,

    @NotNull
    String cron,

    @DurationMin(seconds = 5)
    Duration duration,

    @NotNull
    Boolean enabled
) {}
