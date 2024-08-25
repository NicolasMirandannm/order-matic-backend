package com.ordermatic.app.security.domain.bridge;

public interface PasswordEncryptionBridge {
  String encryptPassword(String password);
  boolean checkPassword(String plainPassword, String encryptedPassword);
}
