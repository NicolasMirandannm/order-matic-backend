package com.ordermatic.app.security.application.customer;

import com.ordermatic.app.security.application.customer.dto.AddressDto;
import com.ordermatic.app.security.application.customer.dto.CustomerUserDto;
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
  public void createCustomerUser(CustomerUserDto customerUser) {
    customerUserCreationService.execute(customerUser);
  }

  @Override
  public void createCustomerAddress(String customerId, AddressDto address) {
    customerAddressCreationService.execute(customerId, address);
  }
}
