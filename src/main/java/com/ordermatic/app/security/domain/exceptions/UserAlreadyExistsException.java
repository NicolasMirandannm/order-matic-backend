package com.ordermatic.app.security.domain.exceptions;

import com.ordermatic.app.security.domain.user.enums.UserType;

public class UserAlreadyExistsException extends DomainSecurityModuleException {
  public UserAlreadyExistsException(String message) {
    super(message);
  }

  public UserAlreadyExistsException(UserType userType) {
    super(userType.getValue().concat(" user already exists."));
  }
}