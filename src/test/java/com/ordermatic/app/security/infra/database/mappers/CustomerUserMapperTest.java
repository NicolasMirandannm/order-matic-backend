package com.ordermatic.app.security.infra.database.mappers;

import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.entities.Address;
import com.ordermatic.app.security.infra.database.collections.user.customer.AddressDocument;
import com.ordermatic.app.security.infra.database.collections.user.customer.CustomerUserCollection;
import com.ordermatic.app.security.infra.database.mappers.user.CustomerUserMapper;
import com.ordermatic.app.security.mocks.CustomerMockFactory;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@Nested
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CustomerUserMapperTest {

  private CustomerUserMapper customerUserMapper;
  private CustomerMockFactory customerMockFactory;

  @BeforeEach
  void setup() {
    customerMockFactory = new CustomerMockFactory();
    customerUserMapper = new CustomerUserMapper();
  }

  @Nested
  class Given_a_customer_user {

    private CustomerUserCollection customerEntity;
    private CustomerUser customerUser;

    @BeforeEach
    void setup() {
      var addressId = new UniqueIdentifier();
      customerEntity = customerMockFactory.createCustomerEntity(new UniqueIdentifier().getValue());
      customerEntity.setAddresses(new ArrayList<>(List.of(customerMockFactory.createAddressEntityWithCondominium(addressId.getValue()))));

      customerUser = customerMockFactory.createCustomerUser(
        new UniqueIdentifier(customerEntity.getId()),
        new ArrayList<>(List.of(customerMockFactory.createAddress(addressId, true, false))),
        null
      );
    }

    @Nested
    class When_mapping_to_domain {
      private CustomerUser mappedCustomerUser;

      @BeforeEach
      void setup() {
        mappedCustomerUser = customerUserMapper.toDomain(customerEntity);
      }

      @Test
      void Then_customer_must_be_mapped() {
        Assertions.assertEquals(customerUser, mappedCustomerUser);
      }
    }

    @Nested
    class When_mapping_to_persistence {
      private CustomerUserCollection mappedCustomerEntity;

      @BeforeEach
      void setup() {
        mappedCustomerEntity = customerUserMapper.toPersistence(customerUser);
      }

      @Test
      void Then_customer_must_be_mapped() {
        Assertions.assertEquals(customerEntity, mappedCustomerEntity);
      }
    }

    @Nested
    class Given_a_main_address {

      private Address mainAddress;
      private AddressDocument mainAddressORM;

      @BeforeEach
      void setup() {
        var addressId = new UniqueIdentifier();
        mainAddress = customerMockFactory.createAddress(addressId, false, false);
        customerUser.setMainAddress(mainAddress);

        mainAddressORM = customerMockFactory.createMainAddressEntity(addressId.getValue());
        var anotherAddressORM =  customerMockFactory.createAddressEntityWithCondominium(new UniqueIdentifier().getValue());
        customerEntity.setAddresses(new ArrayList<>(List.of(mainAddressORM, anotherAddressORM)));
      }

      @Nested
      class When_mapping_to_domain {
        private CustomerUser mappedCustomerUser;

        @BeforeEach
        void setup() {
          mappedCustomerUser = customerUserMapper.toDomain(customerEntity);
        }

        @Test
        void Then_main_address_must_be_mapped() {
          Assertions.assertFalse(mappedCustomerUser.getMainAddress().isEmpty());
          Assertions.assertEquals(mainAddress, mappedCustomerUser.getMainAddress().get());
        }

        @Test
        void Then_addresses_list_must_not_have_main_address() {
          Assertions.assertFalse(mappedCustomerUser.getAddresses().contains(mainAddress));
        }

        @Nested
        class When_mapping_to_persistence {
          private CustomerUserCollection mappedCustomerEntity;

          @BeforeEach
          void setup() {
            mappedCustomerEntity = customerUserMapper.toPersistence(customerUser);
          }

          @Test
          void Then_customer_collection_must_be_have_a_main_address_in_addresses_list() {
            Assertions.assertTrue(mappedCustomerEntity.getAddresses().contains(mainAddressORM));
          }
        }
      }
    }
  }
}
