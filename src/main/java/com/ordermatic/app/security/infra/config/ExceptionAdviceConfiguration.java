package com.ordermatic.app.security.infra.config;

import com.ordermatic.app.security.domain.exceptions.DomainSecurityModuleException;
import com.ordermatic.app.security.domain.exceptions.UserAlreadyExistsException;
import com.ordermatic.shared.exceptions.ExceptionRestDetails;
import com.ordermatic.shared.exceptions.RequiredFieldException;
import com.ordermatic.shared.utilitaires.services.LinkedFieldsValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = "com.ordermatic.app.security")
public class ExceptionAdviceConfiguration {

  @ExceptionHandler(RequiredFieldException.class)
  public ResponseEntity<Map<String, Object>> handleRequiredFieldException(RequiredFieldException ex) {
    ExceptionRestDetails exceptionRestDetails = new ExceptionRestDetails(
      HttpStatus.BAD_REQUEST,
      ex.getMessage(),
      "api/security/domain"
    );

    return new ResponseEntity<>(exceptionRestDetails.getErrorDetails(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DomainSecurityModuleException.class)
  public ResponseEntity<Map<String, Object>> handleSecurityDomainException(DomainSecurityModuleException ex) {
    ExceptionRestDetails exceptionRestDetails = new ExceptionRestDetails(
      HttpStatus.BAD_REQUEST,
      ex.getMessage(),
      "api/security/domain"
    );

    return new ResponseEntity<>(exceptionRestDetails.getErrorDetails(), HttpStatus.BAD_REQUEST);
  }
}
