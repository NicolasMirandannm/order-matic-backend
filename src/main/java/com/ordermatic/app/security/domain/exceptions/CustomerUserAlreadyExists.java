package com.ordermatic.app.security.domain.exceptions;

public class CustomerUserAlreadyExists extends RuntimeException {
  public CustomerUserAlreadyExists(String message) {
    super(message);
  }

  public CustomerUserAlreadyExists() {
    super("Customer user already exists.");
  }
}
