package com.ordermatic.app.security.infra.bcrypt;

import com.ordermatic.app.security.SecurityModuleTest;
import com.ordermatic.app.security.infra.services.bcrypt.BcryptPasswordEncryptionBridge;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

@Nested
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BcryptPasswordEncryptionBridgeTest extends SecurityModuleTest {

  @Autowired
  private BcryptPasswordEncryptionBridge bcryptPasswordEncryptionBridge;

  @Nested
  class Given_a_plain_text_password extends SecurityModuleTest {
    private String passwordPlainText;

    @BeforeEach
    void setup() {
      passwordPlainText = "password123";
    }

    @Nested
    class When_encrypting_the_password extends SecurityModuleTest {
      private String encryptedPassword;

      @BeforeEach
      void setup() {
        encryptedPassword = bcryptPasswordEncryptionBridge.encryptPassword(passwordPlainText);
      }

      @Test
      void Then_should_be_encrypted() {
        Assertions.assertNotEquals(passwordPlainText, encryptedPassword);
      }
    }
  }

  @Nested
  class Given_a_plain_text_password_and_an_encrypted_password extends SecurityModuleTest {
    private String passwordPlainText;
    private String encryptedPassword;

    @BeforeEach
    void setup() {
      passwordPlainText = "password123";
      encryptedPassword = bcryptPasswordEncryptionBridge.encryptPassword(passwordPlainText);
    }

    @Nested
    class When_checking_the_password extends SecurityModuleTest {
      private boolean passwordMatch;

      @BeforeEach
      void setup() {
        passwordMatch = bcryptPasswordEncryptionBridge.checkPassword(passwordPlainText, encryptedPassword);
      }

      @Test
      void Then_should_match() {
        Assertions.assertTrue(passwordMatch);
      }
    }
  }
}
