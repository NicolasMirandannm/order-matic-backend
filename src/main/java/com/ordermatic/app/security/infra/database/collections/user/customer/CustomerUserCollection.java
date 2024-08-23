package com.ordermatic.app.security.infra.database.collections.user.customer;

import com.ordermatic.shared.ddd.InfraEntity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Document(collection = "customer_user")
public class CustomerUserCollection extends InfraEntity {

  @Id
  private UUID id;

  private String name;

  private String email;

  @Field(name = "phone_number")
  private String phone;

  private String cpf;

  private String password;

  private Set<AddressDocument> addresses;
}
