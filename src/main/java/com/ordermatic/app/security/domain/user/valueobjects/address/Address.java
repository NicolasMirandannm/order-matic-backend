package com.ordermatic.app.security.domain.user.valueobjects.address;

import com.ordermatic.shared.ddd.ValueObject;
import lombok.Getter;

@Getter
public class Address extends ValueObject {
    private String street;
    private Integer number;
    private String city;
    private String state;
    private String cep;
    private String reference;
    private Boolean main;
    private Condominium condominium;
    private Apartment apartment;

    public Address() {}

    public Address(String street, Integer number, String city, String state, String cep,
                   String reference, Boolean main, Condominium condominium, Apartment apartment) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.cep = cep;
        this.main = main;
        this.reference = reference;
        this.condominium = condominium;
        this.apartment = apartment;
    }
}
