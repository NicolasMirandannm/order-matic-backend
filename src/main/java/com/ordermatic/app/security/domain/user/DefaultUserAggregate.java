package com.ordermatic.app.security.domain.user;

import com.ordermatic.app.security.domain.user.valueobjects.*;
import com.ordermatic.shared.ddd.AggregateRoot;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.Getter;

@Getter
public abstract class DefaultUserAggregate extends AggregateRoot<DefaultUserAggregate> {
  protected String name;
  protected Email email;
  protected Phone phone;
  protected String password;

  protected DefaultUserAggregate(UniqueIdentifier id) {
    super(id);
  }
}
