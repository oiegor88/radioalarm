package io.radioalarm.api;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ApiConfiguration implements WebMvcConfigurer {

  private final CorsProperties corsProperties;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
        .allowedOrigins(corsProperties.getAllowedOrigins())
        .allowedMethods(corsProperties.getAllowedMethods())
        .allowedHeaders(corsProperties.getAllowedHeaders())
        .allowCredentials(corsProperties.isAllowCredentials());
  }
}
