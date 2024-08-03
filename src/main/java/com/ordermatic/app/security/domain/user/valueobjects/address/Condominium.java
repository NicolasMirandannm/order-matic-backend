package com.ordermatic.app.security.domain.user.valueobjects.address;

import com.ordermatic.shared.exceptions.DomainException;

public record Condominium(String name, Integer houseNumber, String observation) {
    public Condominium {
        if (name == null || name.isBlank()) {
            throw new DomainException("Condominium name cannot be empty.");
        }
        if (houseNumber == null) {
            throw new DomainException("Condominium house number cannot be empty.");
        }
        else if (houseNumber <= 0) {
            throw new DomainException("Condominium house number cannot be less than 1.");
        }
    }
}
