package com.ordermatic.app.security.infra.database.entities.user.customer;

import com.ordermatic.shared.ddd.InfraEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "condominium", schema = "security")
public class CondominiumEntity extends InfraEntity {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "house_number", nullable = false)
  private Integer houseNumber;

  @Column(name = "street", nullable = true)
  private String observation;
}
