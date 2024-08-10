package com.ordermatic.app.security.domain.user.valueobjects;

import com.ordermatic.shared.ddd.ValueObject;
import com.ordermatic.shared.exceptions.DomainException;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class Email extends ValueObject {
  private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

  private final String value;


  public Email(String value) {
    this.value = value != null ? getAVerifiedEmail(value) : null;
  }

  private String getAVerifiedEmail(String email) {
    Pattern pattern = Pattern.compile(EMAIL_REGEX);
    if (!pattern.matcher(email).matches()) {
      throw new DomainException(email.concat(" is a invalid email."));
    }
    return email;
  }
}
