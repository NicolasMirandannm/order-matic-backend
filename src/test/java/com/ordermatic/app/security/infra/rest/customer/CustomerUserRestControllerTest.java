package com.ordermatic.app.security.infra.rest.customer;

import com.ordermatic.app.security.SecurityModuleTest;
import com.ordermatic.app.security.application.customer.dto.AddressDto;
import com.ordermatic.app.security.application.customer.dto.CustomerUserDto;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.valueobjects.Email;
import com.ordermatic.app.security.domain.user.valueobjects.Phone;
import com.ordermatic.app.security.infra.services.jwt.JwtTokenBridgeImpl;
import com.ordermatic.app.security.mocks.CustomerMockFactory;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
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

  @Autowired
  private CustomerUserRepository customerUserRepository;

  @Autowired
  private JwtTokenBridgeImpl jwtService;

  private ObjectMapper objectMapper;
  private CustomerMockFactory customerMockFactory;

  @BeforeEach
  void setup() {
    objectMapper = new ObjectMapper();
    customerMockFactory = new CustomerMockFactory();
  }

  @AfterEach
  void clear() {
    customerUserRepository.deleteAll();
  }

  @Nested
  class Given_a_customer_user_dto {

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
    class When_create_customer_user_is_called {

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
    class When_create_with_empty_values {

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

  @Nested
  class Given_an_address_dto {
    private AddressDto addressDto;
    private String token;

    @BeforeEach
    void setup() {
      addressDto = customerMockFactory.createAddressDto();
    }

    @Nested
    class When_customer_already_exists {
      private CustomerUser customerUser;

      @BeforeEach
      void setup() {
        customerUser = customerMockFactory.createCustomerUser(new UniqueIdentifier());
        customerUserRepository.save(customerUser);
        token = jwtService.generateCustomerJwtToken(customerUser);
      }

      @Nested
      class When_create_address {

        @Test
        void then_address_is_created() throws Exception {
          mockMvc.perform(post("/security/customer-user/address")
            .header("Authorization", "Bearer " + token)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(addressDto))
          ).andExpect(status().isCreated());

          CustomerUser user = customerUserRepository.findById(customerUser.getId()).orElse(null);
          assertNotNull(user);
          assertEquals(1, user.getAddresses().size());
        }
      }
    }

    @Nested
    class When_customer_does_not_exist {
      @Nested
      class When_create_address {
        private CustomerUser invalidCustomerUser;

        @BeforeEach
        void setup() {
          invalidCustomerUser = customerMockFactory.createCustomerUser(new UniqueIdentifier());
          token = jwtService.generateCustomerJwtToken(invalidCustomerUser);
        }

        @Test
        void then_address_is_created() throws Exception {
          mockMvc.perform(post("/security/customer-user/address")
            .header("Authorization", "Bearer " + token)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(addressDto))
          ).andExpect(status().isBadRequest())
            .andExpect(result -> assertTrue(result.getResponse().getContentAsString()
              .contains("User not found with id: " + invalidCustomerUser.getIdValue())));
        }
      }
    }
  }
}
