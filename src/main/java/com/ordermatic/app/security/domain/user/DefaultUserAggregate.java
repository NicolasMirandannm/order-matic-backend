package com.ordermatic.app.security.domain.user;

import com.ordermatic.app.security.domain.user.valueobjects.Email;
import com.ordermatic.app.security.domain.user.valueobjects.Phone;
import com.ordermatic.shared.ddd.AggregateRoot;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(setterPrefix = "with")
public abstract class DefaultUserAggregate extends AggregateRoot {
  protected String name;
  protected Email email;
  protected Phone phone;
  protected String password;

  protected DefaultUserAggregate(UniqueIdentifier id) {
    super(id);
  }
}
