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
@Table(name = "apartment", schema = "security")
public class ApartmentEntity extends InfraEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "number", nullable = false)
  private String number;

  @Column(name = "block", nullable = false)
  private String block;

  @Column(name = "floor", nullable = false)
  private String floor;

  @Column(name = "observation", nullable = true)
  private String observation;
}
