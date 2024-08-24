package com.ordermatic.app.security.mocks;

import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.valueobjects.Cpf;
import com.ordermatic.app.security.domain.user.valueobjects.Email;
import com.ordermatic.app.security.domain.user.valueobjects.Phone;
import com.ordermatic.app.security.domain.user.valueobjects.address.Address;
import com.ordermatic.app.security.domain.user.valueobjects.address.Apartment;
import com.ordermatic.app.security.domain.user.valueobjects.address.Condominium;
import com.ordermatic.app.security.infra.database.collections.user.customer.AddressDocument;
import com.ordermatic.app.security.infra.database.collections.user.customer.ApartmentDocument;
import com.ordermatic.app.security.infra.database.collections.user.customer.CondominiumDocument;
import com.ordermatic.app.security.infra.database.collections.user.customer.CustomerUserCollection;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CustomerMockFactory {
  private final static String DEFAULT_CPF = "854.835.260-40";
  private final static String DEFAULT_EMAIL = "nicolas.dev@ordermatic.com";
  private final static String DEFAULT_NAME = "Nicolas Leonardo Miranda Lima";
  private final static String DEFAULT_PASSWORD = "123456";
  private final static String DEFAULT_PHONE = "12345678901";

  private final static String DEFAULT_CITY = "São Paulo";
  private final static String DEFAULT_STATE = "SP";
  private final static String DEFAULT_STREET = "Paulista";
  private final static Integer DEFAULT_NUMBER = 100;
  private final static String DEFAULT_CEP = "12345678";
  private final static String DEFAULT_REFERENCE = "Near the mall";

  private final static Condominium DEFAULT_CONDOMINIUM = new Condominium("Condominium", 100, null);
  private final static Apartment DEFAULT_APARTMENT = new Apartment("A", "1", "100", null);

  public CustomerUserCollection createCustomerEntity(UUID id) {
    return CustomerUserCollection.builder()
      .id(id)
      .cpf(new Cpf(DEFAULT_CPF).getValue())
      .email(new Email(DEFAULT_EMAIL).getValue())
      .name(DEFAULT_NAME)
      .password(DEFAULT_PASSWORD)
      .phone(new Phone(DEFAULT_PHONE).getValue())
      .build();
  }

  public AddressDocument createMainAddressEntity() {
    return AddressDocument.builder()
      .city(DEFAULT_CITY)
      .state(DEFAULT_STATE)
      .street(DEFAULT_STREET)
      .number(DEFAULT_NUMBER)
      .main(true)
      .cep(DEFAULT_CEP)
      .reference(DEFAULT_REFERENCE)
      .build();
  }

  public AddressDocument createAddressEntityWithCondominium() {
    return AddressDocument.builder()
      .city(DEFAULT_CITY)
      .state(DEFAULT_STATE)
      .street(DEFAULT_STREET)
      .number(DEFAULT_NUMBER)
      .main(false)
      .cep(DEFAULT_CEP)
      .reference(DEFAULT_REFERENCE)
      .condominium(CondominiumDocument.builder()
        .name(DEFAULT_CONDOMINIUM.name())
        .houseNumber(DEFAULT_CONDOMINIUM.houseNumber())
        .build())
      .build();
  }

  public AddressDocument createAddressEntityWithApartment() {
    return AddressDocument.builder()
      .city(DEFAULT_CITY)
      .state(DEFAULT_STATE)
      .street(DEFAULT_STREET)
      .number(DEFAULT_NUMBER)
      .main(false)
      .cep(DEFAULT_CEP)
      .reference(DEFAULT_REFERENCE)
      .apartment(ApartmentDocument.builder()
        .block(DEFAULT_APARTMENT.block())
        .floor(DEFAULT_APARTMENT.floor())
        .number(DEFAULT_APARTMENT.number())
        .build())
      .build();
  }

  public Address createAddress(boolean withCondominium, boolean withApartment) {
    return Address.anAddress()
      .withCity(DEFAULT_CITY)
      .withState(DEFAULT_STATE)
      .withStreet(DEFAULT_STREET)
      .withNumber(DEFAULT_NUMBER)
      .withCep(DEFAULT_CEP)
      .withReference(DEFAULT_REFERENCE)
      .withApartment(withApartment ? DEFAULT_APARTMENT : null)
      .withCondominium(withCondominium ? DEFAULT_CONDOMINIUM : null)
      .build();
  }

  public CustomerUser createCustomerUser(UniqueIdentifier id) {
    return CustomerUser.aCustomerUser()
      .withId(id)
      .withCpf(new Cpf(DEFAULT_CPF))
      .withEmail(new Email(DEFAULT_EMAIL))
      .withName(DEFAULT_NAME)
      .withPassword(DEFAULT_PASSWORD)
      .withPhone(new Phone(DEFAULT_PHONE))
      .build();
  }

  public CustomerUser createCustomerUser(UniqueIdentifier id, List<Address> address, Address mainAddress) {
    return CustomerUser.aCustomerUser()
      .withId(id)
      .withCpf(new Cpf(DEFAULT_CPF))
      .withEmail(new Email(DEFAULT_EMAIL))
      .withName(DEFAULT_NAME)
      .withPassword(DEFAULT_PASSWORD)
      .withPhone(new Phone(DEFAULT_PHONE))
      .withAddresses(address)
      .withMainAddress(mainAddress)
      .build();
  }
}