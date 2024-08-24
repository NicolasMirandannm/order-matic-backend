package com.ordermatic.app.security.infra.bcrypt;

import com.ordermatic.app.security.domain.bridge.PasswordEncryptionBridge;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BcryptPasswordEncryptionBridge implements PasswordEncryptionBridge {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public BcryptPasswordEncryptionBridge(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public String encryptPassword(String password) {
    return passwordEncoder.encode(password);
  }

  @Override
  public boolean checkPassword(String plainPassword, String encryptedPassword) {
    return passwordEncoder.matches(plainPassword, encryptedPassword);
  }
}
