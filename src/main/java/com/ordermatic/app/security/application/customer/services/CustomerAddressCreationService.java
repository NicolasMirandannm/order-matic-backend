package com.ordermatic.app.security.application.customer.services;

import com.ordermatic.app.security.domain.exceptions.UserNotFoundException;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.dto.CustomerAddressDto;
import com.ordermatic.app.security.domain.user.factory.CustomerAddressFactory;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressCreationService {

  private final CustomerUserRepository customerUserRepository;
  private final CustomerAddressFactory customerAddressFactory;

  @Autowired
  public CustomerAddressCreationService(CustomerUserRepository customerUserRepository, CustomerAddressFactory customerAddressFactory) {
    this.customerUserRepository = customerUserRepository;
    this.customerAddressFactory = customerAddressFactory;
  }

  public void execute(String customerId, CustomerAddressDto addressDto) {
    var customerIdentifier = new UniqueIdentifier(customerId);
    CustomerUser customer = customerUserRepository.findById(customerIdentifier)
      .orElseThrow(() -> new UserNotFoundException(customerIdentifier));

    var addressCreated = customerAddressFactory.create(addressDto);
    customer.addNewAddress(addressCreated, addressDto.getIsMain());
    customerUserRepository.save(customer);
  }
}
