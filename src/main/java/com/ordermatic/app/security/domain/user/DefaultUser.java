package com.ordermatic.app.security.domain.user;

import com.ordermatic.shared.ddd.DomainEntity;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;

public class DefaultUser extends DomainEntity<DefaultUser> {
  private String name;
  private String email;
  private String phone;
  private String password;

  protected DefaultUser(UniqueIdentifier id) {
    super(id);
  }
}
