package com.ordermatic.app.security.domain.user;

import com.ordermatic.app.security.domain.user.valueobjects.*;
import com.ordermatic.shared.ddd.DomainEntity;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.Getter;

@Getter
public abstract class DefaultUser extends DomainEntity<DefaultUser> {
  protected String name;
  protected Email email;
  protected Phone phone;
  protected Password password;

  protected DefaultUser(UniqueIdentifier id) {
    super(id);
  }
}
