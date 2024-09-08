package com.ordermatic.app.security.application.customer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CondominiumDto {
  private String block;
  private Integer houseNumber;
  private String observation;
}
