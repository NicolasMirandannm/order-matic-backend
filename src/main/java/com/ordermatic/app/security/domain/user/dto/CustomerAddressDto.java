package com.ordermatic.app.security.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerAddressDto {
  private String id;
  private String street;
  private Integer number;
  private String neighborhood;
  private String city;
  private String state;
  private Boolean isCommercialAddress;
  private String cep;
  private String reference;
  private CondominiumDto condominium;
  private ApartmentDto apartment;

  @Builder.Default
  private Boolean isMain = false;
}