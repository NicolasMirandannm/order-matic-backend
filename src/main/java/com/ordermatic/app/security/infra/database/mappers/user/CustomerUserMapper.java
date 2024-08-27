package com.ordermatic.app.security.infra.database.mappers.user;

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
import com.ordermatic.shared.ddd.Mapper;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class CustomerUserMapper implements Mapper<CustomerUser, CustomerUserCollection> {

  @Override
  public CustomerUser toDomain(@NonNull CustomerUserCollection infra) {
    var mainAddress = infra.getAddresses().stream().filter(AddressDocument::isMain).findFirst().orElse(null);
    infra.getAddresses().remove(mainAddress);

    return CustomerUser.aCustomerUser()
      .withId(new UniqueIdentifier(infra.getId()))
      .withName(infra.getName())
      .withPassword(infra.getPassword())
      .withCpf(new Cpf(infra.getCpf()))
      .withPhone(new Phone(infra.getPhone()))
      .withEmail(new Email(infra.getEmail()))
      .withAddresses(infra.getAddresses().stream().map(this::mapAddress).toList())
      .withMainAddress(nonNull(mainAddress) ? mapAddress(mainAddress) : null)
      .build();
  }

  @Override
  public CustomerUserCollection toPersistence(@NonNull CustomerUser domain) {
    var customerUser = CustomerUserCollection.builder()
      .id(domain.getIdValue())
      .name(domain.getName())
      .password(domain.getPassword())
      .cpf(domain.getCpfValue())
      .phone(domain.getPhoneValue())
      .email(domain.getEmailValue())
      .addresses(domain.getAddresses().stream().map(address -> mapAddressEntity(address, false)).toList())
      .build();

    if (domain.getMainAddress().isPresent()) {
      customerUser.addAddress(mapAddressEntity(domain.getMainAddress().get(), true));
    }
    return customerUser;
  }

  private AddressDocument mapAddressEntity(Address address, boolean isMain) {
    var condominium = address.getCondominium();
    var apartment = address.getApartment();

    var condominiumEntity = isNull(condominium) ? null :
      CondominiumDocument.builder()
        .name(condominium.name())
        .houseNumber(condominium.houseNumber())
        .observation(condominium.observation())
        .build();

    var apartmentEntity = isNull(apartment) ? null :
      ApartmentDocument.builder()
        .block(apartment.block())
        .floor(apartment.floor())
        .number(apartment.number())
        .observation(apartment.observation())
        .build();

    return AddressDocument.builder()
      .city(address.getCity())
      .state(address.getState())
      .street(address.getStreet())
      .number(address.getNumber())
      .cep(address.getCep())
      .main(isMain)
      .reference(address.getReference())
      .condominium(condominiumEntity)
      .apartment(apartmentEntity)
      .build();
  }

  private Address mapAddress(AddressDocument addressEntity) {
    var condominiumEntity = addressEntity.getCondominium();
    var apartmentEntity = addressEntity.getApartment();

    var condominium = nonNull(condominiumEntity)
      ? new Condominium(condominiumEntity.getName(), condominiumEntity.getHouseNumber(), condominiumEntity.getObservation())
      : null;

    var apartment = nonNull(apartmentEntity)
      ? new Apartment(apartmentEntity.getBlock(), apartmentEntity.getFloor(), apartmentEntity.getNumber(), apartmentEntity.getObservation())
      : null;

    return Address.anAddress()
      .withCity(addressEntity.getCity())
      .withState(addressEntity.getState())
      .withStreet(addressEntity.getStreet())
      .withNumber(addressEntity.getNumber())
      .withCep(addressEntity.getCep())
      .withReference(addressEntity.getReference())
      .withCondominium(condominium)
      .withApartment(apartment)
      .build();
  }
}
