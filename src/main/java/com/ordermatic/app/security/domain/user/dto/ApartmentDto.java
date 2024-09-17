package com.ordermatic.app.security.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApartmentDto {
  private String number;
  private String block;
  private String floor;
  private String observation;
}
