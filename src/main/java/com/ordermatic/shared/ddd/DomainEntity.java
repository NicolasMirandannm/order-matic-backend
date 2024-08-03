package com.ordermatic.shared.ddd;

import com.ordermatic.shared.utilitaires.services.AbstractEquals;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public abstract class DomainEntity<T> extends AbstractEquals {
  protected final UniqueIdentifier id;

  protected DomainEntity(UniqueIdentifier id) {
    this.id = id;
    this.idObject = this.getClass().getSimpleName();
  }

  public String getIdValue() {
    return id.getValue();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    DomainEntity<T> other = (DomainEntity<T>) obj;

    if (this.id == null) {
      return other.getId() == null;
    }

    return this.id.equals(other.getId());
  }
}
