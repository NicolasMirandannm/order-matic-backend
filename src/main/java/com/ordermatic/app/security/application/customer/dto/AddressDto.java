package com.ordermatic.app.security.application.customer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
  private String street;
  private Integer number;
  private String city;
  private String state;
  private Boolean isCommercialAddress;
  private String cep;
  private String reference;
  private CondominiumDto condominium;
  private ApartmentDto apartment;
}
