package com.ordermatic.app.security.application.facades;

import com.ordermatic.app.security.domain.user.dto.CustomerAddressDto;
import com.ordermatic.app.security.domain.user.dto.CustomerUserDto;
import com.ordermatic.app.security.domain.user.dto.TokenDto;

public interface CustomerUserFacade {
  TokenDto createCustomerUser(CustomerUserDto customerUser);
  void createCustomerAddress(String customerId, CustomerAddressDto address);
}
