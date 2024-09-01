package com.ordermatic.app.security.infra.rest.customer;

import com.ordermatic.app.security.MongoDbTestContainer;
import com.ordermatic.app.security.SecurityModuleTest;
import com.ordermatic.app.security.application.customer.dto.CustomerUserDto;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.valueobjects.Email;
import com.ordermatic.app.security.domain.user.valueobjects.Phone;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Nested
@EnableAutoConfiguration
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CustomerUserRestControllerTest extends SecurityModuleTest {

  @Autowired
  private CustomerUserRestController customerUserRestController;

  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @Autowired
  private CustomerUserRepository customerUserRepository;

  @BeforeEach
  void setup() {
    objectMapper = new ObjectMapper();
  }

  @AfterEach
  void clear() {
    customerUserRepository.deleteAll();
  }

  @Nested
  class Given_a_customer_user_dto extends SecurityModuleTest {

    private CustomerUserDto customerUserDto;

    @BeforeEach
    void setup() throws Exception {
      customerUserDto = CustomerUserDto.builder()
        .email("nicolas.dev@gmail.com")
        .phoneNumber("99999999999")
        .username("nicolas.dev")
        .password("password")
        .build();
    }

    @Nested
    class When_create_customer_user_is_called extends SecurityModuleTest {

      @Test
      void then_customer_user_is_created() throws Exception {
        mockMvc.perform(post("/security/customer-user")
          .contentType("application/json")
          .content(objectMapper.writeValueAsString(customerUserDto))
        ).andExpect(status().isCreated());

        assertNotNull(customerUserRepository.findByEmailAndPhone(
          new Email(customerUserDto.getEmail()),
          new Phone(customerUserDto.getPhoneNumber())).orElse(null)
        );
      }
    }

    @Nested
    class When_create_with_empty_values extends SecurityModuleTest {

      @Test
      void then_customer_user_is_not_created() throws Exception {
        mockMvc.perform(post("/security/customer-user")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(CustomerUserDto.builder().build()))
          ).andExpect(status().isBadRequest())
          .andExpect(result -> assertTrue(result.getResponse().getContentAsString()
            .contains("Username, Email, Password, Phone Number cannot be empty.")
          ));
      }
    }
  }
}
