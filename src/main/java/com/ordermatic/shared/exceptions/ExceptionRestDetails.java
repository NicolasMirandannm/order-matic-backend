package com.ordermatic.shared.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.Map;

@Getter
public class ExceptionRestDetails {
  private final Map<String, Object> errorDetails;

  public ExceptionRestDetails(HttpStatus status, String message, String module) {
    this.errorDetails = Map.of(
      "timestamp", OffsetDateTime.now(),
      "status", status.value(),
      "error", status.getReasonPhrase(),
      "message", message,
      "module", module
    );
  }
}
