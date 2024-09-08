package com.ordermatic.app.security.domain.user.valueobjects;

import com.ordermatic.shared.exceptions.DomainException;

public record Apartment(String number, String block, String floor, String observation) {
  public Apartment {
    DomainException.throwWhenNullOrEmpty(number, "Apartment number");
    DomainException.throwWhenNullOrEmpty(floor, "Apartment floor");
  }
}
