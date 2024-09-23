package com.ordermatic.app.security.application.customer.services;

import com.ordermatic.app.security.domain.mappers.CustomerAddressMapper;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.dto.CustomerAddressDto;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressUpdateService {

  private final CustomerUserRepository customerUserRepository;
  private final CustomerAddressMapper customerAddressMapper;

  @Autowired
  public CustomerAddressUpdateService(CustomerUserRepository customerUserRepository, CustomerAddressMapper customerAddressMapper) {
    this.customerUserRepository = customerUserRepository;
    this.customerAddressMapper = customerAddressMapper;
  }

  public void execute(String customerId, CustomerAddressDto customerAddressDto) {
    var customer = customerUserRepository.findById(new UniqueIdentifier(customerId))
      .orElse(null); //todo: handle exception
    var updatedAddress = customerAddressMapper.toAddresssEntity(customerAddressDto);
    customer.updateAddress(updatedAddress);
    customerUserRepository.save(customer);
  }
}
