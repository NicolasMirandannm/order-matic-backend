package com.ordermatic.shared.utilitaires;

import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import org.junit.jupiter.api.*;

import java.util.UUID;

@Nested
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UniqueIdentifierTest {
  private UniqueIdentifier uniqueIdentifier;

  @Nested
  public class Given_a_string_value {

    @Nested
    public class When_has_valid_value {

      @BeforeEach
      public void setUp() {
        var value = UUID.randomUUID().toString();
        uniqueIdentifier = new UniqueIdentifier(value);
      }

      @Test
      void Then_should_generate_a_unique_identifier() {
        Assertions.assertNotNull(uniqueIdentifier);
      }
    }

    @Nested
    public class When_has_invalid_value {
      Exception exception;

      @BeforeEach
      void setup() {
        var value = "invalid-format-uuid";
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
          uniqueIdentifier = new UniqueIdentifier(value);
        });
      }

      @Test
      void Then_should_throw_an_exception() {
        var message = "Invalid UUID string to unique identifier: invalid-format-uuid";
        Assertions.assertEquals(message, exception.getMessage());
      }
    }
  }

  @Nested
  public class Given_a_uuid {

    @BeforeEach
    public void setUp() {
      var uuid = UUID.randomUUID();
      uniqueIdentifier = new UniqueIdentifier(uuid);
    }

    @Test
    void Then_should_generate_a_unique_identifier() {
      Assertions.assertNotNull(uniqueIdentifier);
    }
  }

  @Nested
  public class Given_no_value {

    @BeforeEach
    public void setUp() {
      uniqueIdentifier = new UniqueIdentifier();
    }

    @Test
    void Then_should_generate_a_random_unique_identifier() {
      Assertions.assertNotNull(uniqueIdentifier);
    }
  }

}
