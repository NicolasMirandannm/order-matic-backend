package com.ordermatic.app.security.domain.user.factory;

import com.ordermatic.app.security.domain.user.factory.parameters.CustomerAddressFactoryParameter;
import com.ordermatic.app.security.domain.user.valueobjects.address.Address;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CustomerAddressFactoryTest {

  private CustomerAddressFactory customerAddressFactory;

  @BeforeEach
  void setup() {
    customerAddressFactory = new CustomerAddressFactory();
  }

  @Nested
  class Given_a_new_address_parameters {
    private CustomerAddressFactoryParameter customerAddressFactoryParameter;

    @BeforeEach
    void setup() {
      customerAddressFactoryParameter = CustomerAddressFactoryParameter.builder()
        .isCommercialAddress(false)
        .cep("00000-000")
        .street("Rua Teste")
        .number(123)
        .city("Campo Grande")
        .state("Mato Grosso Sul")
        .reference("Near the market")
        .build();
    }

    @Nested
    class When_create_a_simple_address_without_condominium_and_apartment {
      private Address customerAddress;

      @BeforeEach
      void setup() {
        customerAddress = customerAddressFactory.create(customerAddressFactoryParameter);
      }

      @Test
      void Then_address_should_be_created() {
        assertNotNull(customerAddress);
        assertEquals(customerAddress.getCep(), customerAddressFactoryParameter.getCep());
        assertEquals(customerAddress.getStreet(), customerAddressFactoryParameter.getStreet());
        assertEquals(customerAddress.getNumber(), customerAddressFactoryParameter.getNumber());
        assertEquals(customerAddress.getCity(), customerAddressFactoryParameter.getCity());
        assertEquals(customerAddress.getState(), customerAddressFactoryParameter.getState());
        assertEquals(customerAddress.isCommercialAddress(), customerAddressFactoryParameter.getIsCommercialAddress());
        assertEquals(customerAddress.getReference(), customerAddressFactoryParameter.getReference());
        assertNull(customerAddress.getCondominium());
        assertNull(customerAddress.getApartment());
      }
    }

    //todo - when address has condominium
    //todo - when address has apartment
    //todo - when has empty required parameters
  }
}
