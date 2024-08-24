package com.ordermatic.app.security.infra.database.collections.user.customer;

import com.ordermatic.shared.ddd.InfraEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class CondominiumDocument {
  private String name;
  private Integer houseNumber;
  private String observation;
}
