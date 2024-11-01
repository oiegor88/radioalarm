package io.radioalarm.mapper;

import io.radioalarm.data.PlaybackEntity;
import io.radioalarm.model.PlaybackModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaybackMapper {

  @Mapping(source = "name", target = "name")
  @Mapping(source = "cron", target = "cron")
  @Mapping(source = "duration", target = "duration")
  @Mapping(source = "enabled", target = "enabled")
  @Mapping(source = "source", target = "source")
  @Mapping(source = "refId", target = "refId")
  PlaybackModel toModel(PlaybackEntity entity);

  @Mapping(source = "name", target = "name")
  @Mapping(source = "cron", target = "cron")
  @Mapping(source = "duration", target = "duration")
  @Mapping(source = "enabled", target = "enabled")
  @Mapping(source = "source", target = "source")
  @Mapping(ignore = true, target = "refId")
  PlaybackEntity toEntity(PlaybackModel model);
}
