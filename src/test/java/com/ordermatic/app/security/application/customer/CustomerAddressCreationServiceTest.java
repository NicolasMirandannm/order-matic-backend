package com.ordermatic.app.security.application.customer;

import com.ordermatic.app.security.SecurityModuleTest;
import com.ordermatic.app.security.application.customer.dto.AddressDto;
import com.ordermatic.app.security.application.customer.services.CustomerAddressCreationService;
import com.ordermatic.app.security.domain.exceptions.UserNotFoundException;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.mocks.CustomerMockFactory;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

//deve criar um endereço para um cliente existe -- feito
//deve lançar exceção ao tentar criar um endereço para um cliente inexistente -- feito

//quando o cliente não possuir nenhum outro endereço, o mesmo deve ser criado como principal
//quando o cliente possuir outros endereços, e o novo estiver marcado como principal, o antigo principal deve ser desmarcado

@Nested
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CustomerAddressCreationServiceTest extends SecurityModuleTest {

  @Autowired
  private CustomerAddressCreationService customerAddressCreationService;

  @Autowired
  private CustomerUserRepository customerUserRepository;

  private CustomerMockFactory customerMockFactory;

  @BeforeEach
  void setup() {
    customerMockFactory = new CustomerMockFactory();
  }

  @AfterEach
  void tearDown() {
    customerUserRepository.deleteAll();
  }

  @Nested
  class Given_a_new_address {
    private AddressDto addressDto;

    @BeforeEach
    void setup() {
      addressDto = AddressDto.builder()
        .street("fools street")
        .number(123)
        .city("fools city")
        .state("fools state")
        .isCommercialAddress(false)
        .cep("12345678")
        .reference("fools reference")
        .build();

    }

    @Nested
    class When_the_customer_already_exists {
      private CustomerUser customer;

      @BeforeEach
      void setup() {
        customer = customerMockFactory.createCustomerUser(new UniqueIdentifier());
        customerUserRepository.save(customer);
        String customerId = customer.getIdValue();

        customerAddressCreationService.createByCustomerId(customerId, addressDto);
        customer = customerUserRepository.findById(customer.getId()).orElse(null);
      }

      @Test
      void Then_address_should_be_created_for_the_customer() {
        assertEquals(1, customer.getAddresses().size());
      }
    }

    @Nested
    class When_the_customer_does_not_exist {
      private UserNotFoundException exception;
      private String invalidCustomerId;

      @BeforeEach
      void setup() {
        invalidCustomerId = new UniqueIdentifier().getValue();
        exception = assertThrows(UserNotFoundException.class, () ->
          customerAddressCreationService.createByCustomerId(invalidCustomerId, addressDto));
      }

      @Test
      void Then_should_throw_a_UserNotFoundException() {
        assertEquals("User not found with id: ".concat(invalidCustomerId).concat("."), exception.getMessage());
      }
    }
  }
}