package com.ordermatic.app.security.application.customer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApartmentDto {
  private String number;
  private Integer block;
  private String floor;
  private String observation;
}
