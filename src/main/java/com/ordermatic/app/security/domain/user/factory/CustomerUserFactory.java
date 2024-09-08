package com.ordermatic.app.security.domain.user.factory;

import com.ordermatic.app.security.domain.bridge.PasswordEncryptionBridge;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.factory.parameters.CustomerUserFactoryParameter;
import com.ordermatic.app.security.domain.user.valueobjects.Email;
import com.ordermatic.app.security.domain.user.valueobjects.Phone;
import com.ordermatic.shared.utilitaires.services.LinkedFieldsValidation;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserFactory {

  private final PasswordEncryptionBridge passwordEncryptionBridge;

  @Autowired
  public CustomerUserFactory(PasswordEncryptionBridge passwordEncryptionBridge) {
    this.passwordEncryptionBridge = passwordEncryptionBridge;
  }

  public CustomerUser create(@NonNull CustomerUserFactoryParameter parameters) {
    validateCustomerUserParameters(parameters);

    return CustomerUser.aCustomerUser()
      .withId(new UniqueIdentifier())
      .withName(parameters.getUsername())
      .withEmail(new Email(parameters.getEmail()))
      .withPhone(new Phone(parameters.getPhoneNumber()))
      .withPassword(passwordEncryptionBridge.encryptPassword(parameters.getPassword()))
      .build();
  }

  private void validateCustomerUserParameters(CustomerUserFactoryParameter parameters) {
    LinkedFieldsValidation.linkedValidation()
      .ifEmpty(parameters.getUsername(), "Username")
      .ifEmpty(parameters.getEmail(), "Email")
      .ifEmpty(parameters.getPassword(), "Password")
      .ifEmpty(parameters.getPhoneNumber(), "Phone Number")
      .ifAnyExceptionExistsThrow("cannot be empty.");
  }
}
