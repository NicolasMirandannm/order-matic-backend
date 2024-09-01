package com.ordermatic.app.security.infra.database.collections.user.customer;

import com.ordermatic.shared.ddd.InfraEntity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
@Setter
@Builder(toBuilder = false)
@Document(collection = "customer_user")
public class CustomerUserCollection extends InfraEntity {

  @Id
  private String id;

  private String name;

  private String email;

  @Field(name = "phone_number")
  private String phone;

  private String cpf;

  private String password;

  private List<AddressDocument> addresses;

  public void addAddress(AddressDocument address) {
    this.addresses = new ArrayList<>(this.addresses);
    this.addresses.add(address);
  }

  public List<AddressDocument> getAddresses() {
    return isNull(this.addresses) ? new ArrayList<>() : this.addresses;
  }

  @Override
  public void setIdObject(String id) {
    this.id = id;
  }

  @Override
  public String getIdObject() {
    return this.id;
  }
}
