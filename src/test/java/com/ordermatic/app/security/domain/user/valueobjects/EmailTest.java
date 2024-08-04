package com.ordermatic.app.security.domain.user.valueobjects;

import com.ordermatic.shared.exceptions.DomainException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EmailTest {

  private Email email;

  @BeforeEach
  void setUp() {
    email = null;
  }

  @Nested
  class Given_a_valid_email {

    @Nested
    class When_creating_an_email {

      @BeforeEach
      void setup() {
        email = new Email("nicolas.dev@miranda.com");
      }

      @Test
      void Then_value_should_not_be_null() {
        assertNotNull(email);
      }
    }
  }

  @Nested
  class Given_an_invalid_email {

    @Nested
    class When_creating_an_emails_with_invalid_format {

      @ParameterizedTest
      @ValueSource(strings = {
        "nicolas.dev",
        "nicolas.dev@",
        "nicolas.dev@miranda",
        "nicolas.dev@miranda.",
        "nicolas.dev@miranda. c",
        "nicolas.dev@miranda .com",
        "nicolas.dev @miranda.com"
      })
      void Then_should_throw_an_exception(String emailValue) {
        Exception exception = assertThrows(DomainException.class, () -> {
          new Email(emailValue);
        });

        assertEquals(emailValue.concat(" is a invalid email."), exception.getMessage());

      }
    }
  }

}
