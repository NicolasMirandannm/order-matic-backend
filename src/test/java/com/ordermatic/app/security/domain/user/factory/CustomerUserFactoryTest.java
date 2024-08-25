package com.ordermatic.app.security.domain.user.factory;

import com.ordermatic.app.security.SecurityModuleTest;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.factory.parameters.CustomerUserFactoryParameter;
import com.ordermatic.shared.exceptions.DomainException;
import com.ordermatic.shared.exceptions.LinkedFieldsValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerUserFactoryTest extends SecurityModuleTest {

  @Autowired
  private CustomerUserFactory customerUserFactory;

  @Nested
  class Given_new_customer_user {
    private CustomerUserFactoryParameter parameters;

    @BeforeEach
    void setup() {
      parameters = CustomerUserFactoryParameter.builder()
        .username("Nicolas Leonardo Miranda Lima")
        .email("nicolas.dev@gmail.com")
        .password("123456")
        .phoneNumber("11999999999")
        .build();
    }

    @Nested
    class When_create {
      private CustomerUser customerUserCreated;

      @BeforeEach
      void setup() {
        customerUserCreated = customerUserFactory.create(parameters);
      }

      @Test
      void Then_customer_info_props_cannot_be_empty() {
        assertNotNull(customerUserCreated);
        assertNotNull(customerUserCreated.getName());
        assertNotNull(customerUserCreated.getEmail());
        assertNotNull(customerUserCreated.getPassword());
        assertNotNull(customerUserCreated.getPhone());
      }

//      @Test // This test is commented out because the feature is not implemented yet in current branch
//      void Then_customer_password_must_be_encrypted() {
//        assertNotEquals(parameters.getPassword(), customerUserCreated.getPassword());
//      }
    }

    @Nested
    class When_create_with_any_null_parameter {
      private Exception exception;

      @BeforeEach
      void setup() {
        var emptyParameters = CustomerUserFactoryParameter.builder().build();
        exception = assertThrows(LinkedFieldsValidationException.class, () -> customerUserFactory.create(emptyParameters));
      }
      @Test
      void Then_throw_exception() {
        assertEquals("Username, Email, Password, Phone Number cannot be empty.", exception.getMessage());
      }
    }
  }
}
