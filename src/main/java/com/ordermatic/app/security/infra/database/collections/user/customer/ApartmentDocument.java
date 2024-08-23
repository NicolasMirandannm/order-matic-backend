package com.ordermatic.app.security.infra.database.collections.user.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApartmentDocument {
  private String number;
  private String block;
  private String floor;
  private String observation;
}
