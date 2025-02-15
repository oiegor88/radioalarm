package io.radioalarm.api;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ApiConfiguration  {

  @Bean
  public WebMvcConfigurer corsConfigurer(CorsProperties corsProperties) {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(corsProperties.getAllowedOrigins())
            .allowedMethods(corsProperties.getAllowedMethods())
            .allowedHeaders(corsProperties.getAllowedHeaders())
            .allowCredentials(corsProperties.isAllowCredentials())
            .exposedHeaders(
                HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
                HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS
            )
        ;
      }
    };
  }
}
