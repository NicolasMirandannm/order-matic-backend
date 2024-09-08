package com.ordermatic.app.security.domain.user.factory.parameters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerAddressFactoryParameter {

  @Data
  @Builder
  public static class Condominium {
    private String block;
    private Integer houseNumber;
    private String observation;
  }

  @Data
  @Builder
  public static class Apartment {
    private String number;
    private String block;
    private String floor;
    private String observation;
  }

  private String street;
  private Integer number;
  private String city;
  private String state;
  private Boolean isCommercialAddress;
  private String cep;
  private String reference;
  private Condominium condominium;
  private Apartment apartment;
}
