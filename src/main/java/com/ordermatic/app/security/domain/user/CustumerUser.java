package com.ordermatic.app.security.domain.user;

import com.ordermatic.app.security.domain.user.valueobjects.address.Address;
import com.ordermatic.app.security.domain.user.valueobjects.Cpf;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;

public class CustumerUser extends DefaultUser {
  private Address address;
  private Cpf cpf;

  public CustumerUser(UniqueIdentifier id) {
    super(id);
  }
}
