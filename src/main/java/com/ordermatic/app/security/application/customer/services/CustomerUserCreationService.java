package com.ordermatic.app.security.application.customer.services;

import com.ordermatic.app.security.application.customer.dto.CustomerUserDto;
import com.ordermatic.app.security.domain.exceptions.CustomerUserAlreadyExists;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.factory.CustomerUserFactory;
import com.ordermatic.app.security.domain.user.factory.parameters.CustomerUserFactoryParameter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserCreationService {

  private final CustomerUserRepository customerUserRepository;
  private final CustomerUserFactory customerUserFactory;

  @Autowired
  public CustomerUserCreationService(CustomerUserRepository customerUserRepository, CustomerUserFactory customerUserFactory) {
    this.customerUserRepository = customerUserRepository;
    this.customerUserFactory = customerUserFactory;
  }

  public void execute(@NonNull CustomerUserDto customerUserDto) {
    CustomerUserFactoryParameter customerUserFactoryParameter = CustomerUserFactoryParameter.builder()
      .username(customerUserDto.getUsername())
      .password(customerUserDto.getPassword())
      .email(customerUserDto.getEmail())
      .phoneNumber(customerUserDto.getPhoneNumber())
      .build();

    CustomerUser customerUserCreated = customerUserFactory.create(customerUserFactoryParameter);

    if (customerUserRepository.findByEmailAndPhone(customerUserCreated.getEmail(), customerUserCreated.getPhone()).isPresent()) {
      throw new CustomerUserAlreadyExists();
    }
    customerUserRepository.save(customerUserFactory.create(customerUserFactoryParameter));
  }
}
