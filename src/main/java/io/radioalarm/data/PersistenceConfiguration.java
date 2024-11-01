package io.radioalarm.data;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.common.mapper.JacksonMapperModule;
import org.dizitart.no2.repository.ObjectRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class PersistenceConfiguration {

  @Bean
  public Nitrite nitrite() {
    return Nitrite.builder()
        .loadModule(new JacksonMapperModule(new JavaTimeModule()))
        .openOrCreate();
  }

  @Bean
  public ObjectRepository<PlaybackEntity> playbackRepository(Nitrite nitrite) {
    return nitrite.getRepository(PlaybackEntity.class);
  }
}
