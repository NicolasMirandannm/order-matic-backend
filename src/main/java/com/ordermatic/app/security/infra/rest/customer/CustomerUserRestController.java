package com.ordermatic.app.security.infra.rest.customer;


import com.ordermatic.app.security.domain.user.dto.CustomerAddressDto;
import com.ordermatic.app.security.domain.user.dto.CustomerUserDto;
import com.ordermatic.app.security.domain.user.dto.TokenDto;
import com.ordermatic.app.security.application.facades.CustomerUserFacade;
import com.ordermatic.app.security.domain.bridge.JwtTokenBridge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security/customer-user")
@CrossOrigin("*")
public class CustomerUserRestController {
  private final CustomerUserFacade customerUserFacade;
  private final JwtTokenBridge jwtTokenBridge;

  @Autowired
  public CustomerUserRestController(CustomerUserFacade customerUserFacade, JwtTokenBridge jwtTokenBridge) {
    this.customerUserFacade = customerUserFacade;
    this.jwtTokenBridge = jwtTokenBridge;
  }

  @PostMapping
  public ResponseEntity<TokenDto> createCustomerUser(@RequestBody CustomerUserDto customerUser) {
    TokenDto tokenDto = customerUserFacade.createCustomerUser(customerUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(tokenDto);
  }

  @PostMapping("/address")
  public ResponseEntity<?> createCustomerUserAddress(@RequestHeader("Authorization") String token, @RequestBody CustomerAddressDto address) {
    String customerId = jwtTokenBridge.getCustomerIdFromToken(token);
    customerUserFacade.createCustomerAddress(customerId, address);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
