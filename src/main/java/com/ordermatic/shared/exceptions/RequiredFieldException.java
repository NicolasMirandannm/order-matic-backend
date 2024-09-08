package com.ordermatic.shared.exceptions;

public class RequiredFieldException extends DomainException {
  public RequiredFieldException(String message) {
    super(message);
  }
}
