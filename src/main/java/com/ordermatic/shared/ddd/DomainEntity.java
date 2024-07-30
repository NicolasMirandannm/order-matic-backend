package com.ordermatic.shared.ddd;

import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class DomainEntity<T> {
  protected final UniqueIdentifier id;

  protected DomainEntity(UniqueIdentifier id) {
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

    DomainEntity<T> other = (DomainEntity<T>) obj;

    if (this.id == null) {
      return other.getId() == null;
    }

    return this.id.equals(other.getId());
  }

  public boolean strictEquals(Object obj) {
    if (!this.equals(obj)) {
      return false;
    }
    DomainEntity<T> other = (DomainEntity<T>) obj;
    return Objects.equals(this, other);
  }
}
