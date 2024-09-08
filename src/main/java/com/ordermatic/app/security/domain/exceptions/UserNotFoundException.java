package com.ordermatic.app.security.domain.exceptions;

import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(UniqueIdentifier id) {
    super("User not found with id: ".concat(id.getValue()).concat("."));
  }
}
