package com.ordermatic.shared.exceptions;

public class RequiredFieldException extends RuntimeException {
  public RequiredFieldException(String message) {
    super(message);
  }
}
