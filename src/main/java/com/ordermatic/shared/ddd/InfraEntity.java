package com.ordermatic.shared.ddd;

import com.ordermatic.shared.utilitaires.services.AbstractEquals;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.lang.reflect.Field;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public abstract class InfraEntity extends AbstractEquals {

  @CreatedDate
  private OffsetDateTime createdAt;

  @CreatedBy
  private String createdBy;

  @LastModifiedDate
  private OffsetDateTime updatedAt;

  @LastModifiedBy
  private String modifiedBy;

  @Version
  private Integer version;

  protected InfraEntity() {
    super(InfraEntity.class.getSimpleName());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    InfraEntity other = (InfraEntity) obj;

    return Objects.equals(this, other);
  }
}
