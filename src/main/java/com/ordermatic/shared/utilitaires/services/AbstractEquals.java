package com.ordermatic.shared.utilitaires.services;

import jakarta.persistence.Transient;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Objects;

@Slf4j
@SuperBuilder
public abstract class AbstractEquals {

  @Transient
  private final String idObject;

  protected AbstractEquals() {
    this.idObject = this.getClass().getSimpleName();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    Class<?> clazz = this.getClass();
    while (clazz != null) {
      Field[] fields = clazz.getDeclaredFields();
      for (Field field : fields) {
        field.setAccessible(true);
        try {
          Object thisValue = field.get(this);
          Object otherValue = field.get(obj);
          if (!Objects.equals(thisValue, otherValue)) {
            return false;
          }
        } catch (IllegalAccessException e) {
          log.error("Error while comparing {} objects", idObject, e);
          return false;
        }
      }
      clazz = clazz.getSuperclass();
    }
    return true;
  }
}
