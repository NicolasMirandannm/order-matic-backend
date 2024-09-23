package com.ordermatic.app.security.domain.mappers;

import com.ordermatic.app.security.domain.user.dto.ApartmentDto;
import com.ordermatic.app.security.domain.user.dto.CondominiumDto;
import com.ordermatic.app.security.domain.user.dto.CustomerAddressDto;
import com.ordermatic.app.security.domain.user.entities.Address;
import com.ordermatic.app.security.domain.user.valueobjects.Apartment;
import com.ordermatic.app.security.domain.user.valueobjects.Condominium;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import org.springframework.stereotype.Component;

@Component
public class CustomerAddressMapper {

  public CustomerAddressDto toAddressDto(Address customerAddress) {
    if (customerAddress == null) {
      return null;
    }
    return CustomerAddressDto.builder()
      .id(customerAddress.getId().getValue())
      .street(customerAddress.getStreet())
      .number(customerAddress.getNumber())
      .neighborhood(customerAddress.getNeighborhood())
      .city(customerAddress.getCity())
      .state(customerAddress.getState())
      .isCommercialAddress(customerAddress.isCommercialAddress())
      .cep(customerAddress.getCep())
      .reference(customerAddress.getReference())
      .condominium(toCondominiumDto(customerAddress.getCondominium()))
      .apartment(toApartmentDto(customerAddress.getApartment()))
      .build();
  }

  public Address toAddresssEntity(CustomerAddressDto customerAddressDto) {
    if (customerAddressDto == null) return null;

    return Address.anAddress()
      .withId(new UniqueIdentifier(customerAddressDto.getId()))
      .withStreet(customerAddressDto.getStreet())
      .withNumber(customerAddressDto.getNumber())
      .withNeighborhood(customerAddressDto.getNeighborhood())
      .withCity(customerAddressDto.getCity())
      .withState(customerAddressDto.getState())
      .withCommercialAddress(customerAddressDto.getIsCommercialAddress())
      .withCep(customerAddressDto.getCep())
      .withReference(customerAddressDto.getReference())
      .withCondominium(toCondominium(customerAddressDto.getCondominium()))
      .withApartment(toApartment(customerAddressDto.getApartment()))
      .build();
  }

  public CondominiumDto toCondominiumDto(Condominium condominium) {
    if (condominium == null) return null;

    return CondominiumDto.builder()
      .block(condominium.block())
      .houseNumber(condominium.houseNumber())
      .observation(condominium.observation())
      .build();
  }

  public Condominium toCondominium(CondominiumDto condominiumDto) {
    if (condominiumDto == null) return null;
    return new Condominium(condominiumDto.getBlock(), condominiumDto.getHouseNumber(), condominiumDto.getObservation());
  }

  public ApartmentDto toApartmentDto(Apartment apartment) {
    if (apartment == null) return null;

    return ApartmentDto.builder()
      .block(apartment.block())
      .number(apartment.number())
      .observation(apartment.observation())
      .floor(apartment.floor())
      .build();
  }

  public Apartment toApartment(ApartmentDto apartmentDto) {
    if (apartmentDto == null) return null;
    return new Apartment(apartmentDto.getBlock(), apartmentDto.getNumber(), apartmentDto.getObservation(), apartmentDto.getFloor());
  }

}
