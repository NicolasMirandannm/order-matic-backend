package com.ordermatic.app.security.infra.rest.customer;


import com.ordermatic.app.security.application.customer.dto.AddressDto;
import com.ordermatic.app.security.application.customer.dto.CustomerUserDto;
import com.ordermatic.app.security.application.facades.CustomerUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security/customer-user")
@CrossOrigin("*")
public class CustomerUserRestController {
  private final CustomerUserFacade customerUserFacade;

  @Autowired
  public CustomerUserRestController(CustomerUserFacade customerUserFacade) {
    this.customerUserFacade = customerUserFacade;
  }

  @PostMapping
  public ResponseEntity<?> createCustomerUser(@RequestBody CustomerUserDto customerUser) {
    customerUserFacade.createCustomerUser(customerUser);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{customerId}/address")
  public ResponseEntity<?> createCustomerUserAddress(@PathVariable String customerId, @RequestBody AddressDto address) {
    customerUserFacade.createCustomerAddress(customerId, address);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
