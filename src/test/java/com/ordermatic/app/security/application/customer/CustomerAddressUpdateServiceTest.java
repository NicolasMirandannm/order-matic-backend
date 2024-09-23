package com.ordermatic.app.security.application.customer;

import com.ordermatic.app.security.SecurityModuleTest;
import com.ordermatic.app.security.application.customer.services.CustomerAddressUpdateService;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.dto.CustomerAddressDto;
import com.ordermatic.app.security.domain.user.entities.Address;
import com.ordermatic.app.security.mocks.CustomerMockFactory;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Nested
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CustomerAddressUpdateServiceTest extends SecurityModuleTest {

  @Autowired
  private CustomerAddressUpdateService customerAddressUpdateService;

  @Autowired
  private CustomerUserRepository customerUserRepository;

  @Autowired
  private CustomerMockFactory customerMockFactory;

  @Nested
  class Given_a_existing_customer_user {
    private CustomerUser customer;
    private Address customerAddress;

    @BeforeEach
    void setup() {
      customer = customerMockFactory.createCustomerUser(new UniqueIdentifier());
      customerAddress = customerMockFactory.createAddress(new UniqueIdentifier(), true, true);
      customer.addNewAddress(customerAddress, true);
      customerUserRepository.save(customer);
    }

    @Nested
    class Given_a_valid_customer_address_dto {
      private CustomerAddressDto customerAddressDto;
      private String updatedStreet;

      @BeforeEach
      void setup() {
        updatedStreet = "new street";
        customerAddressDto = customerMockFactory.createAddressDto(customer.getMainAddress().getId());
        customerAddressDto.setStreet(updatedStreet);
      }

      @Nested
      class When_update_customer_address {

        @BeforeEach
        void setup() {
          customerAddressUpdateService.execute(customer.getIdValue(), customerAddressDto);
          customer = customerUserRepository.findById(customer.getId()).orElse(null);
        }

        @Test
        void should_update_customer_address() {
          assertEquals(updatedStreet, customer.getMainAddress().getStreet());
        }
      }
    }
  }
}
