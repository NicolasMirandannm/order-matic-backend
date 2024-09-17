package com.ordermatic.app.security.domain.user.dto;

import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class TokenDto {
  private final String token;
  private final OffsetDateTime generatedAt;

  public TokenDto(String token) {
    this.token = token;
    this.generatedAt = OffsetDateTime.now();
  }
}
