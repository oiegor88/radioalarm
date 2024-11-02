package io.radioalarm.data;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.common.mapper.JacksonMapperModule;
import org.dizitart.no2.mvstore.MVStoreModule;
import org.dizitart.no2.repository.ObjectRepository;
import org.dizitart.no2.store.StoreModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class PersistenceConfiguration {

  @Bean
  public StoreModule storageModule(@Value("${nitrite.database.file}") String databaseFile) {
    return MVStoreModule.withConfig()
        .filePath(databaseFile)
        .build();
  }

  @Bean
  public Nitrite nitrite(StoreModule storeModule) {
    return Nitrite.builder()
        .loadModule(new JacksonMapperModule(new JavaTimeModule()))
        .loadModule(storeModule)
        .openOrCreate();
  }

  @Bean
  public ObjectRepository<PlaybackEntity> playbackObjectRepository(Nitrite nitrite) {
    return nitrite.getRepository(PlaybackEntity.class);
  }
}
