package com.ordermatic.app.security.infra.database.entities.user;

import com.ordermatic.shared.ddd.InfraEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AddressEntity extends InfraEntity<AddressEntity> {
  private String street;
  private Integer number;
  private String city;
  private String state;
  private String cep;
  private String reference;
  private UUID condominium;
  private UUID apartment;
}
