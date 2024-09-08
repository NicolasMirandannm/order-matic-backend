package com.ordermatic.shared.utilitaires.services;

import com.ordermatic.shared.exceptions.RequiredFieldException;
import com.ordermatic.shared.utilitaires.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LinkedFieldsValidation {

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

    public LinkedFieldsList ifNull(Object value, String label) {
      if (value == null)
        this.messages.add(label);
      return this;
    }

    public void ifAnyExceptionExistsThrow(String endMessage) {
      if (!this.messages.isEmpty()) {
        throw new RequiredFieldException(String.join(", ", this.messages).concat(" ").concat(endMessage));
      }
    }
  }

  public static LinkedFieldsList linkedValidation() {
    return new LinkedFieldsList();
  }
}
