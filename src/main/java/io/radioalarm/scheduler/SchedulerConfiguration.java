package io.radioalarm.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration(proxyBeanMethods = false)
public class SchedulerConfiguration {

  public static final int MAX_TASKS = 5;

  @Bean
  public ThreadPoolTaskScheduler taskScheduler() {
    var taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setThreadNamePrefix("playback");
    taskScheduler.setPoolSize(MAX_TASKS);
    taskScheduler.initialize();
    return taskScheduler;
  }
}
