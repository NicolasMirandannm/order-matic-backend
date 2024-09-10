package com.ordermatic.app.security.infra.services.jwt;

import com.ordermatic.app.security.domain.bridge.JwtTokenBridge;
import com.ordermatic.app.security.domain.user.CustomerUser;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtTokenBridgeImpl implements JwtTokenBridge {
  private final JwtEncoder jwtEncoder;
  private final JwtDecoder jwtDecoder;

  public JwtTokenBridgeImpl(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
    this.jwtEncoder = jwtEncoder;
    this.jwtDecoder = jwtDecoder;
  }

  @Override
  public String generateCustomerJwtToken(CustomerUser customerUser) {

    var claims = JwtClaimsSet.builder()
      .issuer("ordermatic")
      .subject(customerUser.getIdValue())
      .issuedAt(Instant.now())
      .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  @Override
  public String getCustomerIdFromToken(String token) {
    return jwtDecoder.decode(token.replace("Bearer ", "")).getSubject();
  }
}
