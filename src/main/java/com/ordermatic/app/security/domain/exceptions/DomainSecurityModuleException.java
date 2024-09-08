package com.ordermatic.app.security.domain.exceptions;

import com.ordermatic.shared.exceptions.DomainException;

public class DomainSecurityModuleException extends DomainException {
  public DomainSecurityModuleException(String message) {
    super(message);
  }
}
