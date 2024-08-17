package com.ordermatic.app.security.domain.user.valueobjects.address;

import com.ordermatic.shared.exceptions.DomainException;
import com.ordermatic.shared.utilitaires.services.NumberUtils;

public record Condominium(String name, Integer houseNumber, String observation) {
  public Condominium {
    DomainException.throwWhenNullOrEmpty(name, "Condominium name");
    if (!NumberUtils.isPositive(houseNumber)) {
      throw new DomainException("Condominium house number cannot be less than 1.");
    }
  }
}
