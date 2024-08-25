package com.ordermatic.app.security.domain.factories;

import com.ordermatic.app.security.SecurityModuleTest;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerUserFactoryTest extends SecurityModuleTest {

  @Autowired
  private CustomerUserFactory customerUserFactory;

  @Nested
  class Given_new_customer_user {
    
  }
}
