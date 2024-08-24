package com.ordermatic.app.security.domain.user;

import com.ordermatic.app.security.domain.user.valueobjects.Cpf;
import com.ordermatic.app.security.domain.user.valueobjects.Email;
import com.ordermatic.app.security.domain.user.valueobjects.address.Address;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@SuperBuilder(builderMethodName = "aCustomerUser", setterPrefix = "with")
public class CustomerUser extends DefaultUserAggregate {
  private List<Address> addresses;
  private Address mainAddress;
  private Cpf cpf;

  public CustomerUser(UniqueIdentifier id, String name, Email email, String password, List<Address> addresses, Address mainAddress, Cpf cpf) {
    super(id);
    this.name = name;
    this.email = email;
    this.password = password;
    this.addresses = addresses;
    this.mainAddress = mainAddress;
    this.cpf = cpf;
  }

  public Optional<Address> getMainAddress() {
    return Optional.ofNullable(mainAddress);
  }
}
