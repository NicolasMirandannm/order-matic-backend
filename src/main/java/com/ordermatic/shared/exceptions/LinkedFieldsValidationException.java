package com.ordermatic.shared.exceptions;

import com.ordermatic.shared.utilitaires.services.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LinkedFieldsValidationException extends RuntimeException {
  public LinkedFieldsValidationException(String message) {
    super(message);
  }

  public static class LinkedFieldsList {
    private final List<String> messages;

    protected LinkedFieldsList() {
      this.messages = new ArrayList<>();
    }

    public LinkedFieldsList ifEmpty(String value, String label) {
      if (StringUtils.isNullOrEmpty(value))
        this.messages.add(label);
      return this;
    }

    public void ifAnyExceptionExistsThrow(String endMessage) {
      if (!this.messages.isEmpty()) {
        throw new LinkedFieldsValidationException(String.join(", ", this.messages).concat(" ").concat(endMessage));
      }
    }


  }

  public static LinkedFieldsList linkedValidation() {
    return new LinkedFieldsList();
  }
}
