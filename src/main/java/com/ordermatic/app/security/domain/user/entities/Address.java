package com.ordermatic.app.security.domain.user.entities;

import com.ordermatic.app.security.domain.user.valueobjects.Apartment;
import com.ordermatic.app.security.domain.user.valueobjects.Condominium;
import com.ordermatic.shared.ddd.DomainEntity;
import com.ordermatic.shared.ddd.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(builderMethodName = "anAddress", setterPrefix = "with")
public class Address extends DomainEntity {
  private String street;
  private Integer number;
  private String neighborhood;
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
