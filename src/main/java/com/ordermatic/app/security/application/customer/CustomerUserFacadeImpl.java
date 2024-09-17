package com.ordermatic.app.security.application.customer;

import com.ordermatic.app.security.domain.user.dto.CustomerAddressDto;
import com.ordermatic.app.security.domain.user.dto.CustomerUserDto;
import com.ordermatic.app.security.domain.user.dto.TokenDto;
import com.ordermatic.app.security.application.customer.services.CustomerAddressCreationService;
import com.ordermatic.app.security.application.customer.services.CustomerUserCreationService;
import com.ordermatic.app.security.application.facades.CustomerUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserFacadeImpl implements CustomerUserFacade {

  private final CustomerUserCreationService customerUserCreationService;
  private final CustomerAddressCreationService customerAddressCreationService;

  @Autowired
  public CustomerUserFacadeImpl(CustomerUserCreationService customerUserCreationService, CustomerAddressCreationService customerAddressCreationService) {
    this.customerUserCreationService = customerUserCreationService;
    this.customerAddressCreationService = customerAddressCreationService;
  }

  @Override
  public TokenDto createCustomerUser(CustomerUserDto customerUser) {
    return customerUserCreationService.execute(customerUser);
  }

  @Override
  public void createCustomerAddress(String customerId, CustomerAddressDto address) {
    customerAddressCreationService.execute(customerId, address);
  }
}
