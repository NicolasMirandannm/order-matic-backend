package com.ordermatic.app.security.domain.user.valueobjects.address;

import com.ordermatic.shared.exceptions.DomainException;
import com.ordermatic.shared.utilitaires.utils.NumberUtils;

public record Condominium(String block, Integer houseNumber, String observation) {
  public Condominium {
    DomainException.throwWhenNullOrEmpty(block, "Condominium block");
    if (!NumberUtils.isPositive(houseNumber)) {
      throw new DomainException("Condominium house number cannot be less than 1.");
    }
  }
}
