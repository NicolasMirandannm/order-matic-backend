package com.ordermatic.app.security.application.customer.services;

import com.ordermatic.app.security.domain.bridge.JwtTokenBridge;
import com.ordermatic.app.security.domain.exceptions.UserAlreadyExistsException;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.dto.CustomerUserDto;
import com.ordermatic.app.security.domain.user.dto.TokenDto;
import com.ordermatic.app.security.domain.user.enums.UserType;
import com.ordermatic.app.security.domain.user.factory.CustomerUserFactory;
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
    CustomerUser customerUser = customerUserFactory.create(customerUserDto);

    if (customerUserRepository.findByEmailAndPhone(customerUser.getEmail(), customerUser.getPhone()).isPresent()) {
      throw new UserAlreadyExistsException(UserType.CUSTOMER);
    }

    customerUserRepository.save(customerUser);
    return new TokenDto(jwtTokenBridge.generateCustomerJwtToken(customerUser));
  }
}
