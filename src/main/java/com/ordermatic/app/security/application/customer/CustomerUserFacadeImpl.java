package com.ordermatic.app.security.application.customer;

import com.ordermatic.app.security.application.customer.dto.CustomerUserDto;
import com.ordermatic.app.security.application.customer.services.CustomerUserCreationService;
import com.ordermatic.app.security.application.facades.CustomerUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserFacadeImpl implements CustomerUserFacade {

  private final CustomerUserCreationService customerUserCreationService;

  @Autowired
  public CustomerUserFacadeImpl(CustomerUserCreationService customerUserCreationService) {
    this.customerUserCreationService = customerUserCreationService;
  }

  @Override
  public void createCustomerUser(CustomerUserDto customerUser) {
    customerUserCreationService.execute(customerUser);
  }
}
