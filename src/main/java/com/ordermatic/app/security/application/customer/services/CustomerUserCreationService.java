package com.ordermatic.app.security.application.customer.services;

import com.ordermatic.app.security.application.customer.dto.CustomerUserDto;
import com.ordermatic.app.security.application.customer.dto.TokenDto;
import com.ordermatic.app.security.domain.bridge.JwtTokenBridge;
import com.ordermatic.app.security.domain.exceptions.UserAlreadyExistsException;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.enums.UserType;
import com.ordermatic.app.security.domain.user.factory.CustomerUserFactory;
import com.ordermatic.app.security.domain.user.factory.parameters.CustomerUserFactoryParameter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserCreationService {

  private final CustomerUserRepository customerUserRepository;
  private final CustomerUserFactory customerUserFactory;
  private final JwtTokenBridge jwtTokenBridge;

  @Autowired
  public CustomerUserCreationService(CustomerUserRepository customerUserRepository, CustomerUserFactory customerUserFactory, JwtTokenBridge jwtTokenBridge) {
    this.customerUserRepository = customerUserRepository;
    this.customerUserFactory = customerUserFactory;
    this.jwtTokenBridge = jwtTokenBridge;
  }

  public TokenDto execute(@NonNull CustomerUserDto customerUserDto) {
    CustomerUserFactoryParameter customerUserFactoryParameter = CustomerUserFactoryParameter.builder()
      .username(customerUserDto.getUsername())
      .password(customerUserDto.getPassword())
      .email(customerUserDto.getEmail())
      .phoneNumber(customerUserDto.getPhoneNumber())
      .build();

    CustomerUser customerUserCreated = customerUserFactory.create(customerUserFactoryParameter);

    if (customerUserRepository.findByEmailAndPhone(customerUserCreated.getEmail(), customerUserCreated.getPhone()).isPresent()) {
      throw new UserAlreadyExistsException(UserType.CUSTOMER);
    }

    customerUserRepository.save(customerUserFactory.create(customerUserFactoryParameter));
    return new TokenDto(jwtTokenBridge.generateCustomerJwtToken(customerUserCreated));
  }
}
