package com.ordermatic.app.security.domain.user.factory;

import com.ordermatic.app.security.domain.user.factory.parameters.CustomerAddressFactoryParameter;
import com.ordermatic.app.security.domain.user.valueobjects.address.Address;
import com.ordermatic.shared.exceptions.RequiredFieldException;
import org.junit.jupiter.api.*;

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
        .isCommercialAddress(true)
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

    @Nested
    class When_create_a_simple_address_with_condominium {
      private Address customerAddress;
      private CustomerAddressFactoryParameter.Condominium condominium;

      @BeforeEach
      void setup() {
        condominium = CustomerAddressFactoryParameter.Condominium.builder()
          .block("A")
          .houseNumber(123)
          .observation("Near the market")
          .build();

        customerAddressFactoryParameter.setCondominium(condominium);
        customerAddress = customerAddressFactory.create(customerAddressFactoryParameter);
      }

      @Test
      void Then_address_should_be_created() {
        assertNotNull(customerAddress.getCondominium());
        assertEquals(condominium.getBlock(), customerAddress.getCondominium().block());
        assertEquals(condominium.getHouseNumber(), customerAddress.getCondominium().houseNumber());
        assertEquals(condominium.getObservation(), customerAddress.getCondominium().observation());
      }
    }

    @Nested
    class When_create_a_simple_address_with_apartment {
      private Address customerAddress;
      private CustomerAddressFactoryParameter.Apartment apartment;

      @BeforeEach
      void setup() {
        apartment = CustomerAddressFactoryParameter.Apartment.builder()
          .number("123")
          .block("A")
          .floor("1")
          .observation("Near the market")
          .build();

        customerAddressFactoryParameter.setApartment(apartment);
        customerAddress = customerAddressFactory.create(customerAddressFactoryParameter);
      }

      @Test
      void Then_address_should_be_created() {
        assertNotNull(customerAddress.getApartment());
        assertEquals(apartment.getNumber(), customerAddress.getApartment().number());
        assertEquals(apartment.getBlock(), customerAddress.getApartment().block());
        assertEquals(apartment.getFloor(), customerAddress.getApartment().floor());
        assertEquals(apartment.getObservation(), customerAddress.getApartment().observation());
      }
    }

    @Nested
    class When_create_an_address_with_null_value_to_is_commercial_address {
      private Address customerAddress;

      @BeforeEach
      void setup() {
        customerAddressFactoryParameter.setIsCommercialAddress(null);
        customerAddress = customerAddressFactory.create(customerAddressFactoryParameter);
      }

      @Test
      void Then_address_should_be_created_with_false_to_is_commercial_address() {
        assertNotNull(customerAddress);
        assertFalse(customerAddress.isCommercialAddress());
      }
    }

    @Nested
    class When_create_an_address_with_empty_required_parameters {
      private RequiredFieldException exception;

      @BeforeEach
      void setup() {
        exception = assertThrows(RequiredFieldException.class, () ->
          customerAddressFactory.create(CustomerAddressFactoryParameter.builder().build()));
      }

      @Test
      void Then_should_throw_exception_to_required_parameters() {
        assertEquals("CEP, Street, Number, City, State, Reference are required to Address creation.", exception.getMessage());
      }
    }
  }
}
