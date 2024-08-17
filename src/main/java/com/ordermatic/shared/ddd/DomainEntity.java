package com.ordermatic.shared.ddd;

import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.shared.utilitaires.services.AbstractEquals;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@SuperBuilder(setterPrefix = "with")
public abstract class DomainEntity extends AbstractEquals {
  protected final UniqueIdentifier id;

  protected DomainEntity(UniqueIdentifier id) {
    super(DomainEntity.class.getSimpleName());
    this.id = id;
  }

  public String getIdValue() {
    return id.getValue();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    DomainEntity other = (DomainEntity) obj;

    if (this.id == null) {
      return other.getId() == null;
    }

    return this.id.equals(other.getId());
  }
}
