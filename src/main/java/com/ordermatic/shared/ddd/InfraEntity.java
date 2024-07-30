package com.ordermatic.shared.ddd;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
public abstract class InfraEntity<T> {
  protected String id;
  private String version;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

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

  public boolean strictEquals(Object obj) {
    if (!this.equals(obj)) {
      return false;
    }
    InfraEntity<T> other = (InfraEntity<T>) obj;
    return Objects.equals(this, other);
  }
}
