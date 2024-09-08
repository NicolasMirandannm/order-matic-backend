package com.ordermatic.app.security.application.facades;

import com.ordermatic.app.security.application.customer.dto.AddressDto;
import com.ordermatic.app.security.application.customer.dto.CustomerUserDto;

public interface CustomerUserFacade {
  void createCustomerUser(CustomerUserDto customerUser);
  void createCustomerAddress(String customerId, AddressDto address);
}
