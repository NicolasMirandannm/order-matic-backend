package com.ordermatic.shared.utilitaires.valueobjs;

import com.ordermatic.shared.ddd.ValueObject;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class UniqueIdentifier extends ValueObject {

  private final String value;

  public UniqueIdentifier(@NonNull String uuidValue) {
    super();
    this.value = generateUUIDFromValue(uuidValue);
  }

  public UniqueIdentifier(@NonNull UUID uuid) {
    super();
    this.value = generateFromUUID(uuid);
  }

  public UniqueIdentifier() {
    super();
    this.value = generateRandomUUID();
  }


  private static String generateRandomUUID() {

    return UUID.randomUUID().toString();
  }

  private static String generateUUIDFromValue(String value) {
    try {
      return generateFromUUID(UUID.fromString(value));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid UUID string to unique identifier: " + value);
    }
  }

  private static String generateFromUUID(UUID uuid) {
    return uuid.toString();
  }
}
