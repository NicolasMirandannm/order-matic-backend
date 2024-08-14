package com.ordermatic.shared.utilitaires.services;

import lombok.NonNull;

public class NumberUtils {
  public static boolean isPositive(@NonNull Integer number) {
    return number > 0;
  }
}
