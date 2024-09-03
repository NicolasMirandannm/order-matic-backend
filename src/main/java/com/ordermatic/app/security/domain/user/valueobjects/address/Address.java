package com.ordermatic.app.security.domain.user.valueobjects.address;

import com.ordermatic.shared.ddd.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(builderMethodName = "anAddress", setterPrefix = "with")
public class Address extends ValueObject {
  private String street;
  private Integer number;
  private String city;
  private String state;
  private Boolean commercialAddress;
  private String cep;
  private String reference;
  private Condominium condominium;
  private Apartment apartment;

  public boolean isCommercialAddress() {
    return commercialAddress;
  }
}
