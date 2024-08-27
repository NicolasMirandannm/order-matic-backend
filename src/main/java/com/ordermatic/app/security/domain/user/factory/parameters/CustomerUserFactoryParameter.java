package com.ordermatic.app.security.domain.user.factory.parameters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerUserFactoryParameter {
  private String username;
  private String password;
  private String email;
  private String phoneNumber;
}
