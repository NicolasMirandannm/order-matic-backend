package com.ordermatic.shared.ddd;

import com.ordermatic.shared.utilitaires.services.AbstractEquals;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
@Getter
@SuperBuilder(setterPrefix = "with")
public abstract class DomainEntity extends AbstractEquals {
  protected final UniqueIdentifier id;

  protected DomainEntity(UniqueIdentifier id) {
    this.id = id;
  }

  public String getIdValue() {
    return isNull(id) ? null : id.getValue();
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
