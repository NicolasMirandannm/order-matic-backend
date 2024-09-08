package com.ordermatic.shared.utilitaires.utils;

public class StringUtils {

  public static boolean isNullOrEmpty(String value) {
    return value == null || value.isBlank();
  }

  public static boolean anyValueIsNullOrEmpty(String... values) {
    for (String value : values) {
      if (isNullOrEmpty(value)) {
        return true;
      }
    }
    return false;
  }

}
