package com.ordermatic.app.security.domain.user.valueobjects.address;

public record Apartment(String number, String block, String floor, String observation) {
    public Apartment {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Apartment number cannot be empty.");
        }
        if (floor == null || floor.isBlank()) {
            throw new IllegalArgumentException("Apartment floor cannot be empty.");
        }
    }
}
