package com.ordermatic.app.security.infra.database.entities.user.customer;

import com.ordermatic.shared.ddd.InfraEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "customer_address", schema = "security")
public class AddressEntity extends InfraEntity {

  @Column(name = "street")
  private String street;

  @Column(name = "number")
  private Integer number;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "cep")
  private String cep;

  @Column(name = "reference")
  private String reference;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerUserEntity customer;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "condominium_id")
  private CondominiumEntity condominium;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "apartment_id")
  private ApartmentEntity apartment;
}
