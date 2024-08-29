package com.ordermatic.app.security.infra.config;

import com.ordermatic.shared.exceptions.LinkedFieldsValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = "com.ordermatic.app.security")
public class ExceptionAdviceConfiguration {

  @ExceptionHandler(LinkedFieldsValidationException.class)
  public ResponseEntity<Map<String, Object>> handleLinkedFieldsValidationException(LinkedFieldsValidationException ex) {
    Map<String, Object> errorDetails = new HashMap<>();
    errorDetails.put("timestamp", OffsetDateTime.now());
    errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
    errorDetails.put("error", "Bad Request");
    errorDetails.put("message", ex.getMessage());
    errorDetails.put("module", "/api/security");

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
}
