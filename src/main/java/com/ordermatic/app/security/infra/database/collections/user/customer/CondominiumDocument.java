package com.ordermatic.app.security.infra.database.collections.user.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CondominiumDocument {
  private String block;
  private Integer houseNumber;
  private String observation;
}
