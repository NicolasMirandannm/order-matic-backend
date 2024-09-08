package com.ordermatic.app.security.application.facades;

import com.ordermatic.app.security.application.customer.dto.AddressDto;
import com.ordermatic.app.security.application.customer.dto.CustomerUserDto;
import com.ordermatic.app.security.application.customer.dto.TokenDto;

public interface CustomerUserFacade {
  TokenDto createCustomerUser(CustomerUserDto customerUser);
  void createCustomerAddress(String customerId, AddressDto address);
}
