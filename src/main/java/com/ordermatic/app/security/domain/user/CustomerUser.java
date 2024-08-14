package com.ordermatic.app.security.domain.user;

import com.ordermatic.app.security.domain.user.valueobjects.Cpf;
import com.ordermatic.app.security.domain.user.valueobjects.address.Address;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(builderClassName = "aCustomerUser", setterPrefix = "with")
public class CustomerUser extends DefaultUserAggregate {
  private List<Address> addresses;
  private Address mainAddress;
  private Cpf cpf;

  public CustomerUser(UniqueIdentifier id) {
    super(id);
  }
}
