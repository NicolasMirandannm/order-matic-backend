package com.ordermatic.app.security.infra.database.entities.user.customer;

import com.ordermatic.shared.ddd.InfraEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "custumer_user", schema = "security")
public class CustomerUserEntity extends InfraEntity {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "phone_number", nullable = false)
  private String phone;

  @Column(name = "cpf", nullable = false)
  private String cpf;

  @Column(name = "password", nullable = false)
  private String password;

  @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = AddressEntity.class)
  private List<AddressEntity> addresses;

}
