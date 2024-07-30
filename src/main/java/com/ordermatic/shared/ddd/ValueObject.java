package com.ordermatic.shared.ddd;

import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class ValueObject<T> {
  private final T value;

  protected ValueObject(T value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    ValueObject<T> other = (ValueObject<T>) obj;

    if (this.value == null) {
      return other.getValue() == null;
    }

    return Objects.equals(this.value, other.getValue());
  }
}
