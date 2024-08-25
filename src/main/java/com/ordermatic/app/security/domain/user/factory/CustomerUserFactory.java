package com.ordermatic.app.security.domain.user.factory;

import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.factory.parameters.CustomerUserFactoryParameter;
import com.ordermatic.app.security.domain.user.valueobjects.Email;
import com.ordermatic.app.security.domain.user.valueobjects.Phone;
import com.ordermatic.shared.exceptions.DomainException;
import com.ordermatic.shared.exceptions.LinkedFieldsValidationException;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserFactory {

  public CustomerUser create(@NonNull CustomerUserFactoryParameter parameters) {
    validateCustomerUserParameters(parameters);

    return CustomerUser.aCustomerUser()
      .withName(parameters.getUsername())
      .withEmail(new Email(parameters.getEmail()))
      .withPhone(new Phone(parameters.getPhoneNumber()))
      .withPassword(parameters.getPassword())
      .build();
  }

  private void validateCustomerUserParameters(CustomerUserFactoryParameter parameters) {
    LinkedFieldsValidationException.linkedValidation()
      .ifEmpty(parameters.getUsername(), "Username")
      .ifEmpty(parameters.getEmail(), "Email")
      .ifEmpty(parameters.getPassword(), "Password")
      .ifEmpty(parameters.getPhoneNumber(), "Phone Number")
      .ifAnyExceptionExistsThrow("cannot be empty.");
  }
}
