package com.ordermatic.app.security.domain.user.enums;

import lombok.Getter;

@Getter
public enum UserType {
  CUSTOMER("Customer"),
  EMPLOYEE("Employee"),
  ADMIN("Admin");

  private final String value;

  UserType(String value) {
    this.value = value;
  }
}
