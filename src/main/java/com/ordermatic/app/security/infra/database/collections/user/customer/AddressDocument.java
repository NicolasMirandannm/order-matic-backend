package com.ordermatic.app.security.infra.database.collections.user.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDocument {
  private String street;
  private Integer number;
  private String city;
  private String state;
  private String cep;
  private Boolean main;
  private String reference;
  private CondominiumDocument condominium;
  private ApartmentDocument apartment;

  public Boolean isMain() {
    return main;
  }
}
