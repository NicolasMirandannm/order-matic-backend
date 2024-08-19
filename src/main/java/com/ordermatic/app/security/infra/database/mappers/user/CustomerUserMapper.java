package com.ordermatic.app.security.infra.database.mappers.user;

import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.valueobjects.Cpf;
import com.ordermatic.app.security.domain.user.valueobjects.Email;
import com.ordermatic.app.security.domain.user.valueobjects.Phone;
import com.ordermatic.app.security.domain.user.valueobjects.address.Address;
import com.ordermatic.app.security.domain.user.valueobjects.address.Apartment;
import com.ordermatic.app.security.domain.user.valueobjects.address.Condominium;
import com.ordermatic.app.security.infra.database.entities.user.customer.AddressEntity;
import com.ordermatic.app.security.infra.database.entities.user.customer.ApartmentEntity;
import com.ordermatic.app.security.infra.database.entities.user.customer.CondominiumEntity;
import com.ordermatic.app.security.infra.database.entities.user.customer.CustomerUserEntity;
import com.ordermatic.shared.ddd.Mapper;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class CustomerUserMapper implements Mapper<CustomerUser, CustomerUserEntity> {

  @Override
  public CustomerUser toDomain(@NonNull CustomerUserEntity infra) {
    return CustomerUser.aCustomerUser()
      .withId(new UniqueIdentifier(infra.getId()))
      .withName(infra.getName())
      .withPassword(infra.getPassword())
      .withCpf(new Cpf(infra.getCpf()))
      .withPhone(new Phone(infra.getPhone()))
      .withEmail(new Email(infra.getEmail()))
      .withAddresses(infra.getAddresses().stream().map(this::mapAddress).toList())
      .build();
  }

  @Override
  public CustomerUserEntity toPersistence(@NonNull CustomerUser domain) {
    return CustomerUserEntity.builder()
      .id(UUID.fromString(domain.getIdValue()))
      .name(domain.getName())
      .password(domain.getPassword())
      .cpf(domain.getCpf().getValue())
      .phone(domain.getPhone().getValue())
      .email(domain.getEmail().getValue())
      .addresses(domain.getAddresses().stream().map(this::mapAddressEntity).toList())
      .build();
  }

  private AddressEntity mapAddressEntity(Address address) {
    var condominium = address.getCondominium();
    var apartment = address.getApartment();

    var condominiumEntity = isNull(condominium) ? null :
      CondominiumEntity.builder()
        .name(condominium.name())
        .houseNumber(condominium.houseNumber())
        .observation(condominium.observation())
        .build();

    var apartmentEntity = isNull(apartment) ? null :
      ApartmentEntity.builder()
        .block(apartment.block())
        .floor(apartment.floor())
        .number(apartment.number())
        .observation(apartment.observation())
        .build();

    return AddressEntity.builder()
      .city(address.getCity())
      .state(address.getState())
      .street(address.getStreet())
      .number(address.getNumber())
      .cep(address.getCep())
      .reference(address.getReference())
      .condominium(condominiumEntity)
      .apartment(apartmentEntity)
      .build();
  }

  private Address mapAddress(AddressEntity addressEntity) {
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
