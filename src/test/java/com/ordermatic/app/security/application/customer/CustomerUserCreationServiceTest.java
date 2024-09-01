package com.ordermatic.app.security.application.customer;

import com.ordermatic.app.security.SecurityModuleTest;
import com.ordermatic.app.security.application.customer.dto.CustomerUserDto;
import com.ordermatic.app.security.application.customer.services.CustomerUserCreationService;
import com.ordermatic.app.security.domain.exceptions.CustomerUserAlreadyExists;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.valueobjects.Email;
import com.ordermatic.app.security.domain.user.valueobjects.Phone;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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

      @AfterEach
      void clean() {
        customerUserRepository.deleteAll();
      }

      @Test
      void Then_customer_user_should_be_created() {
        assertNotNull(customerUserCreated);
      }
    }

    @Nested
    class When_already_exists_another_customer_user_with_email_and_phone extends SecurityModuleTest {

      @BeforeEach
      void setup() {
        customerUserCreationService.execute(customerUserDto);
      }

      @AfterEach
      void clean() {
        customerUserRepository.deleteAll();
      }

      @Nested
      class When_create extends SecurityModuleTest {
        private CustomerUserAlreadyExists exception;

        @BeforeEach
        void setup() {
          exception = assertThrows(CustomerUserAlreadyExists.class, () -> {
            customerUserCreationService.execute(customerUserDto);
          });
        }

        @Test
        void Then_should_throw_an_exception() {
          assertNotNull(exception);
          assertEquals("Customer user already exists.", exception.getMessage());
        }
      }
    }
  }
}
