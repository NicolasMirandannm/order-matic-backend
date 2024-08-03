package com.ordermatic.shared.ddd;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Objects;

@Getter
@Slf4j
public abstract class ValueObject {
  private final String objectName;

  protected ValueObject() {
    this.objectName = this.getClass().getSimpleName();
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
          log.error("Error while comparing {} value object", objectName, e);
          return false;
        }
      }
      clazz = clazz.getSuperclass();
    }
    return true;
  }
}
