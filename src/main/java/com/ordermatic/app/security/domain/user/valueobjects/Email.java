package com.ordermatic.app.security.domain.user.valueobjects;

import com.ordermatic.shared.ddd.ValueObject;

public class Email extends ValueObject {
  private final String value;

  public Email() {
    this.value = null;
  }

  public Email(String value) {
    this.value = value;
  }


}
