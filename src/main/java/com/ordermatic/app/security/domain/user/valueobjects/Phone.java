package com.ordermatic.app.security.domain.user.valueobjects;

import com.ordermatic.shared.ddd.ValueObject;
import com.ordermatic.shared.exceptions.DomainException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class Phone extends ValueObject {
  private final String value;
  private final static Integer PHONE_LENGTH = 9;
  private final static Integer DDD_LENGTH = 2;

  public Phone(String value) {
    this.value = value != null ? getValidPhoneNumber(value) : null;
  }

  private static String getValidPhoneNumber(String value) {
    var unmaskedValue = value.replaceAll("[^0-9]", "");

    if (unmaskedValue.length() != DDD_LENGTH + PHONE_LENGTH) {
      throw new DomainException("Phone number must have 11 digits.");
    }

    var phoneNumber = unmaskedValue.substring(unmaskedValue.length() - PHONE_LENGTH);
    var ddd = unmaskedValue.substring(0, DDD_LENGTH);

    if (!isValidDDD(ddd)) {
      throw new DomainException(ddd + " is not a valid DDD.");
    }
    return ddd + phoneNumber;
  }

  private static boolean isValidDDD(String ddd) {
    Integer[] validDDDs = {
      11, 12, 13, 14, 15, 16, 17, 18, 19,
      21, 22, 24, 27, 28, 31, 32, 33, 34,
      35, 37, 38, 41, 42, 43, 44, 45, 46,
      47, 48, 49, 51, 53, 54, 55, 61, 62,
      64, 63, 65, 66, 67, 68, 69, 71, 73,
      74, 75, 77, 79, 81, 82, 83, 84, 85,
      86, 87, 88, 89, 91, 92, 93, 94, 95,
      96, 97, 98, 99};

    return Arrays.stream(validDDDs).anyMatch(dddValue -> dddValue.equals(Integer.parseInt(ddd)));
  }

}
