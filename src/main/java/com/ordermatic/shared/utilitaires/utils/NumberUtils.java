package com.ordermatic.shared.utilitaires.utils;

import lombok.NonNull;

public class NumberUtils {
  public static boolean isPositive(@NonNull Integer number) {
    return number > 0;
  }
}
