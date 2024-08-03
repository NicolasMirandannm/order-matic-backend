package com.ordermatic.shared.ddd;

import com.ordermatic.shared.utilitaires.services.AbstractEquals;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@Slf4j
public abstract class InfraEntity<T> extends AbstractEquals {
  protected String id;
  private String version;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  protected InfraEntity() {
    this.idObject = this.getClass().getSimpleName();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    InfraEntity<T> other = (InfraEntity<T>) obj;

    if (this.id == null) {
      return other.getId() == null;
    }

    return this.id.equals(other.getId());
  }
}
