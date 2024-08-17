package com.ordermatic.shared.exceptions;

import com.ordermatic.shared.utilitaires.services.StringUtils;

public class DomainException extends RuntimeException {

  public DomainException(String message) {
    super(message);
  }

  public DomainException(String message, Throwable cause) {
    super(message, cause);
  }

  public static void throwWhenNullOrEmpty(String value, String label) {
    if (StringUtils.isNullOrEmpty(value)) {
      throw new DomainException(label + " cannot be empty.");
    }
  }
}
