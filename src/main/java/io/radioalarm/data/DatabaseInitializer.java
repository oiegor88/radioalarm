package io.radioalarm.data;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.dizitart.no2.repository.ObjectRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Deprecated
public class DatabaseInitializer implements ApplicationRunner {

  private final ObjectRepository<PlaybackEntity> nitriteRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    var radio1 = new PlaybackEntity();
    radio1.setId(UUID.randomUUID());
    radio1.setName("Radio Polska 24");
    radio1.setCron("0 0 10 * * *");
    radio1.setDuration(Duration.ofMinutes(1));
    radio1.setSource(new URL("http://mp3.polskieradio.pl:8080/"));
    radio1.setEnabled(true);
    nitriteRepository.insert(radio1);

    var radio2 = new PlaybackEntity();
    radio2.setId(UUID.randomUUID());
    radio2.setName("Radio Caprice");
    radio2.setCron("0 10 12 * * *");
    radio2.setDuration(Duration.ofHours(1).plus(Duration.ofMinutes(30)));
    radio2.setSource(new URL("http://mp3.polskieradio.pl:9090/"));
    radio2.setEnabled(false);
    nitriteRepository.insert(radio2);
  }
}
