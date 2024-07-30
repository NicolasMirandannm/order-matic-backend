package com.ordermatic.shared.utilitaires.valueobjs;

import com.ordermatic.shared.ddd.ValueObject;
import lombok.NonNull;

import java.util.UUID;

public class UniqueIdentifier extends ValueObject<String> {
  public UniqueIdentifier(@NonNull String uuidValue) {
    super(generateUUIDFromValue(uuidValue));
  }

  public UniqueIdentifier(@NonNull UUID uuid) {
    super(generateFromUUID(uuid));
  }

  public UniqueIdentifier() {
    super(generateRandomUUID());
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
