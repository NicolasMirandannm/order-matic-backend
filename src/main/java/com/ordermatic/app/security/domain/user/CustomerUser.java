package com.ordermatic.app.security.domain.user;

import com.ordermatic.app.security.domain.user.valueobjects.Cpf;
import com.ordermatic.app.security.domain.user.valueobjects.address.Address;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.Getter;

@Getter
public class CustomerUser extends DefaultUserAggregate {
  private Address address;
  private Cpf cpf;

  public CustomerUser(UniqueIdentifier id) {
    super(id);
  }
}
