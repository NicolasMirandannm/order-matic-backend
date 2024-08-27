package com.ordermatic.shared.ddd;

import com.ordermatic.shared.utilitaires.services.AbstractEquals;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

@Getter
@Setter
public abstract class InfraEntity extends AbstractEquals {

  @CreatedDate
  protected OffsetDateTime createdAt;

  @CreatedBy
  protected String createdBy;

  @LastModifiedDate
  protected OffsetDateTime updatedAt;

  @LastModifiedBy
  protected String modifiedBy;

  @Version
  protected Integer version;

  protected abstract void setIdObject(String id);
  protected abstract String getIdObject();
}
