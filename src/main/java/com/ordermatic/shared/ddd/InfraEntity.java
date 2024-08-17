package com.ordermatic.shared.ddd;

import com.ordermatic.shared.utilitaires.services.AbstractEquals;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Slf4j
@SuperBuilder
@MappedSuperclass
public abstract class InfraEntity extends AbstractEquals {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Version
  @Column(name = "version")
  private Integer version;

  @Column(name = "created_at")
  private OffsetDateTime createdAt;

  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

  protected InfraEntity() {
    super(InfraEntity.class.getSimpleName());
  }

  @PrePersist
  protected void onCreate() {
    this.createdAt = OffsetDateTime.now();
    this.updatedAt = OffsetDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = OffsetDateTime.now();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    InfraEntity other = (InfraEntity) obj;

    if (this.id == null) {
      return other.getId() == null;
    }

    return this.id.equals(other.getId());
  }
}
