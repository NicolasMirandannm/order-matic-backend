package com.ordermatic.app.security.application.customer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerUserDto {
  private String id;
  private String username;
  private String password;
  private String email;
  private String phoneNumber;
  private String cpf;
}
