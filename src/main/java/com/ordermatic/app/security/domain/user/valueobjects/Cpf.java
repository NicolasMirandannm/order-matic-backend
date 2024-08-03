package com.ordermatic.app.security.domain.user.valueobjects;

import com.ordermatic.shared.ddd.ValueObject;
import com.ordermatic.shared.exceptions.DomainException;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Cpf extends ValueObject {

  private final String value;

  public Cpf() {
    this.value = null;
  }

  public Cpf(@NonNull String value) {
    this.value = getValidCpf(value);
  }

  private static String getCpfWithoutMask(String value) {
    return value.replaceAll("[^0-9]", "");
  }

  private static String getValidCpf(String value) {
    var cpf = getCpfWithoutMask(value);
    if (cpf.length() != 11) {
      throw new DomainException("Invalid amount of numeric characters for CPF.");
    }
    else if (allDigitsAreEqual(cpf) || !verifyCpfDigits(cpf)) {
      throw new DomainException(cpf + " is a invalid CPF.");
    }
    return cpf;
  }

  private static boolean allDigitsAreEqual(String cpf) {
    return cpf.matches("(\\d)\\1{10}");
  }

  private static boolean verifyCpfDigits(String cpf) {
    int sum = 0;
    for (int i = 0; i < 9; i++) {
      sum += (cpf.charAt(i) - '0') * (10 - i);
    }
    int firstVerifierDigit = 11 - (sum % 11);
    firstVerifierDigit = (firstVerifierDigit >= 10) ? 0 : firstVerifierDigit;

    sum = 0;
    for (int i = 0; i < 10; i++) {
      sum += (cpf.charAt(i) - '0') * (11 - i);
    }
    int secondVerifierDigit = 11 - (sum % 11);
    secondVerifierDigit = (secondVerifierDigit >= 10) ? 0 : secondVerifierDigit;

    return cpf.charAt(9) - '0' == firstVerifierDigit && cpf.charAt(10) - '0' == secondVerifierDigit;
  }
}
