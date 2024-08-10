package com.ordermatic.app.security.domain.user.valueobjects;

import com.ordermatic.shared.exceptions.DomainException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Nested
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PhoneTest {

  private Phone phone;

  @BeforeEach
  void setup() {
    phone = null;
  }

  @Nested
  class Given_a_valid_phone_number {

    @Nested
    class When_creating_a_phone_with_mask {

      @BeforeEach
      void setup() {
        phone = new Phone("(67) 99999-9999");
      }

      @Test
      void Then_the_number_should_be_set_without_mask() {
        assertEquals("67999999999", phone.getValue());
      }
    }
  }

  @Nested
  class Given_an_invalid_phone_number {

    @Nested
    class When_creating_a_phone_with_invalid_amount_of_digits {
      Exception exception;

      @BeforeEach
      void setup() {
        exception = assertThrows(DomainException.class, () -> phone = new Phone("67 99999-99"));
      }

      @Test
      void Then_should_throw_a_domain_exception_because_of_digits_amount() {
        assertEquals("Phone number must have 11 digits.", exception.getMessage());
      }
    }

    @Nested
    class When_creating_a_phone_with_invalid_ddd {
      Exception exception;

      @BeforeEach
      void setup() {
        exception = assertThrows(DomainException.class, () -> phone = new Phone("(00) 99999-9999"));
      }

      @Test
      void Then_should_throw_a_domain_exception_because_of_ddd_format() {
        assertEquals("00 is not a valid DDD.", exception.getMessage());
      }
    }
  }
}
