package com.ordermatic.app.security.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfiguration {

  @Value("${security.password.encoder.strength}")
  private int strength;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(strength);
  }
}
