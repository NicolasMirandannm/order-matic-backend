package com.ordermatic.app.security.infra.database.mappers;

import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.infra.database.entities.user.customer.CustomerUserEntity;
import com.ordermatic.app.security.infra.database.mappers.user.CustomerUserMapper;
import com.ordermatic.app.security.mocks.CustomerMockFactory;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.UUID;

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

    private CustomerUserEntity customerEntity;
    private CustomerUser customerUser;

    @BeforeEach
    void setup() {
      var mainAddressORM = customerMockFactory.createMainAddressEntity();
      var optionalAddressORM = customerMockFactory.createAddressEntityWithCondominium();
      customerEntity = customerMockFactory.createCustomerEntity(UUID.randomUUID());
      customerEntity.setAddresses(List.of(mainAddressORM, optionalAddressORM));

      var mainAddress = customerMockFactory.createAddress(false, false);
      var optionalAddress = customerMockFactory.createAddress(true, false);
      customerUser = customerMockFactory.createCustomerUser(
        new UniqueIdentifier(customerEntity.getId()),
        List.of(mainAddress, optionalAddress),
        mainAddress
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
      private CustomerUserEntity mappedCustomerEntity;

      @BeforeEach
      void setup() {
        mappedCustomerEntity = customerUserMapper.toPersistence(customerUser);
      }

      @Test
      void Then_customer_must_be_mapped() {
        Assertions.assertEquals(customerEntity, mappedCustomerEntity);
      }
    }
  }
}
