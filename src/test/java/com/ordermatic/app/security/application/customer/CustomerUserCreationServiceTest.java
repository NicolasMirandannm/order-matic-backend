package com.ordermatic.app.security.application.customer;

import com.ordermatic.app.security.SecurityModuleTest;
import com.ordermatic.app.security.application.customer.dto.CustomerUserDto;
import com.ordermatic.app.security.application.customer.services.CustomerUserCreationService;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.valueobjects.Email;
import com.ordermatic.app.security.domain.user.valueobjects.Phone;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Nested
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CustomerUserCreationServiceTest extends SecurityModuleTest {

  @Autowired
  private CustomerUserCreationService customerUserCreationService;

  @Autowired
  private CustomerUserRepository customerUserRepository;

  @Nested
  class Given_a_customer_user_dto extends SecurityModuleTest {
    private CustomerUserDto customerUserDto;

    @BeforeEach
    void setup() {
      customerUserDto = CustomerUserDto.builder()
        .username("Nicolas Leonardo Miranda Lima")
        .password("123456")
        .email("nicolas.dev@gmail.com")
        .phoneNumber("11999999999")
        .build();
    }

    @Nested
    class When_create_customer_user extends SecurityModuleTest {
      private CustomerUser customerUserCreated;

      @BeforeEach
      void setup() {
        customerUserCreationService.execute(customerUserDto);
        customerUserCreated = customerUserRepository.findByEmailAndPhone(
          new Email(customerUserDto.getEmail()),
          new Phone(customerUserDto.getPhoneNumber())
        ).orElseThrow();
      }

      @Test
      void Then_customer_user_should_be_created() {
        assertNotNull(customerUserCreated);
      }
    }

    //todo: if any user already exists with the same email and phone number, it should throw an exception

  }

}
