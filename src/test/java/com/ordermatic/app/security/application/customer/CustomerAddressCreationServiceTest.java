package com.ordermatic.app.security.application.customer;

import com.ordermatic.app.security.SecurityModuleTest;
import com.ordermatic.app.security.application.customer.dto.AddressDto;
import com.ordermatic.app.security.application.customer.services.CustomerAddressCreationService;
import com.ordermatic.app.security.domain.exceptions.UserNotFoundException;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.entities.address.Address;
import com.ordermatic.app.security.mocks.CustomerMockFactory;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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

        customerAddressCreationService.execute(customerId, addressDto);
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
          customerAddressCreationService.execute(invalidCustomerId, addressDto));
      }

      @Test
      void Then_should_throw_a_UserNotFoundException() {
        assertEquals("User not found with id: ".concat(invalidCustomerId).concat("."), exception.getMessage());
      }
    }
  }

  @Nested
  class Given_a_customer_with_another_address {
    private CustomerUser customer;
    private Address anotherAddress;

    @BeforeEach
    void setup() {
      customer = customerMockFactory.createCustomerUser(new UniqueIdentifier());
      anotherAddress = customerMockFactory.createAddress(new UniqueIdentifier(), true, false);
      customer.addNewAddress(anotherAddress, true);
      customerUserRepository.save(customer);
    }

    @Nested
    class When_the_new_address_is_marked_as_main {
      private AddressDto addressDto;

      @BeforeEach
      void setup() {
        addressDto = customerMockFactory.createAddressDto();
        addressDto.setIsMain(true);
      }

      @Nested
      class When_create {
        private Address newMainAddress;

        @BeforeEach
        void setup() {
          customerAddressCreationService.execute(customer.getIdValue(), addressDto);
          customer = customerUserRepository.findById(customer.getId()).orElseThrow();
          newMainAddress = customer.getMainAddress().orElse(null);
        }

        @Test
        void Then_customer_should_have_only_one_old_address_in_addresses_list() {
          assertEquals(1, customer.getAddresses().size());
          assertTrue(customer.getAddresses().contains(anotherAddress));
        }

        @Test
        void Then_customer_main_address_should_be_the_new_address() {
          assertNotEquals(anotherAddress, newMainAddress);
        }
      }
    }
  }
}