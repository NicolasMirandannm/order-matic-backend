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

import static java.util.Objects.isNull;

@Getter
@Setter
@SuperBuilder(builderMethodName = "aCustomerUser", setterPrefix = "with")
public class CustomerUser extends AbstractUserAggregate {
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

  public List<Address> getAddresses() {
    return isNull(addresses) ? List.of() : addresses;
  }

  public String getCpfValue() {
    return isNull(cpf) ? null : cpf.getValue();
  }

  public String getPhoneValue() {
    return isNull(phone) ? null : phone.getValue();
  }

  public String getEmailValue() {
    return isNull(email) ? null : email.getValue();
  }
}
