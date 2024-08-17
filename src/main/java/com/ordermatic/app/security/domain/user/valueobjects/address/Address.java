package com.ordermatic.app.security.domain.user.valueobjects.address;

import com.ordermatic.shared.ddd.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(builderClassName = "anAddress", setterPrefix = "with")
public class Address extends ValueObject {
  private String street;
  private Integer number;
  private String city;
  private String state;
  private String cep;
  private String reference;
  private Condominium condominium;
  private Apartment apartment;
}
