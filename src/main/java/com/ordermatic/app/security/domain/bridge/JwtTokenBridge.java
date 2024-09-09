package com.ordermatic.app.security.domain.bridge;

import com.ordermatic.app.security.domain.user.CustomerUser;

public interface JwtTokenBridge {
  String generateCustomerJwtToken(CustomerUser customerId);
  String getCustomerIdFromToken(String token);
}
