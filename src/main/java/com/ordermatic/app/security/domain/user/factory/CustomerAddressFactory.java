package com.ordermatic.app.security.domain.user.factory;

import com.ordermatic.app.security.domain.user.factory.parameters.CustomerAddressFactoryParameter;
import com.ordermatic.app.security.domain.user.valueobjects.address.Address;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CustomerAddressFactory {
  public Address create(@NonNull CustomerAddressFactoryParameter customerAddressFactoryParameter) {
    return Address.anAddress()
      .withCep(customerAddressFactoryParameter.getCep())
      .withStreet(customerAddressFactoryParameter.getStreet())
      .withNumber(customerAddressFactoryParameter.getNumber())
      .withCity(customerAddressFactoryParameter.getCity())
      .withState(customerAddressFactoryParameter.getState())
      .withCommercialAddress(customerAddressFactoryParameter.getIsCommercialAddress())
      .withReference(customerAddressFactoryParameter.getReference())
      .build();
  }
}
