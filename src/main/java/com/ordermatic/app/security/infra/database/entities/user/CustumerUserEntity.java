package com.ordermatic.app.security.infra.database.entities.user;

import com.ordermatic.shared.ddd.InfraEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CustumerUserEntity extends InfraEntity<CustumerUserEntity> {
  private String name;
  private String email;
  private String phone;
  private String cpf;
  private String password;
  private AddressEntity mainAddress;
  private List<AddressEntity> addresses;
}
