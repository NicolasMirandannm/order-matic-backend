package com.ordermatic.app.security.domain.user.valueobjects;

import com.ordermatic.shared.exceptions.DomainException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CpfTest {

  private Cpf cpf;

  @Nested
  class Given_a_valid_cpf {

    @Nested
    class When_instantiated_with_masked_value {

      @BeforeEach
      void setUp() {
        cpf = new Cpf("123.456.789-09");
      }

      @Test
      void Then_the_value_should_be_set() {
        assertNotNull(cpf.getValue());
      }

      @Test
      void Then_the_value_should_be_set_without_mask() {
        assertEquals("12345678909", cpf.getValue());
      }
    }

    @Nested
    class When_instantioned_with_unmasked_value {

      @BeforeEach
      void setUp() {
        cpf = new Cpf("12345678909");
      }

      @Test
      void Then_the_value_should_be_set() {
        assertNotNull(cpf.getValue());
      }
    }

    @Nested
    class When_instantiated_with_invalid_value {

      @Nested
      class When_value_has_invalid_length {
        private Exception exception;

        @BeforeEach
        void setUp() {
          exception = assertThrows(Exception.class, () -> new Cpf("1234567890"));
        }

        @Test
        void Then_should_throw_exception() {
          assertEquals("Invalid amount of numeric characters for CPF.", exception.getMessage());
        }
      }

      @Nested
      class When_value_has_invalid_characters {
        private Exception exception;

        @BeforeEach
        void setUp() {
          exception = assertThrows(Exception.class, () -> new Cpf("1234567890a"));
        }

        @Test
        void Then_should_throw_exception() {
          assertEquals("Invalid amount of numeric characters for CPF.", exception.getMessage());
        }
      }

      @Nested
      class When_is_invalid_cpf {

        @Test
        void Then_should_throw_exception_when_has_invalid_verify_digits() {
          Exception exception = assertThrows(DomainException.class, () -> new Cpf("12345678900"));
          assertEquals("12345678900 is a invalid CPF.", exception.getMessage());
        }

        @Test
        void Then_should_throw_exception_when_has_all_digits_equals() {
          Exception exception = assertThrows(DomainException.class, () -> new Cpf("111.111.111-11"));
          assertEquals("11111111111 is a invalid CPF.", exception.getMessage());
        }
      }
    }
  }
}
