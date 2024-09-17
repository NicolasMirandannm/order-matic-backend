package com.ordermatic.app.security.domain.user.factory;

import com.ordermatic.app.security.domain.user.dto.ApartmentDto;
import com.ordermatic.app.security.domain.user.dto.CondominiumDto;
import com.ordermatic.app.security.domain.user.dto.CustomerAddressDto;
import com.ordermatic.app.security.domain.user.entities.Address;
import com.ordermatic.app.security.domain.user.valueobjects.Apartment;
import com.ordermatic.app.security.domain.user.valueobjects.Condominium;
import com.ordermatic.shared.utilitaires.services.LinkedFieldsValidation;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class CustomerAddressFactory {
  public Address create(@NonNull CustomerAddressDto customerAddressDto) {
    requiredFieldsVerification(customerAddressDto);

    var isCommercialAddress = customerAddressDto.getIsCommercialAddress();
    return Address.anAddress()
      .withId(new UniqueIdentifier())
      .withCep(customerAddressDto.getCep())
      .withStreet(customerAddressDto.getStreet())
      .withNeighborhood(customerAddressDto.getNeighborhood())
      .withNumber(customerAddressDto.getNumber())
      .withCity(customerAddressDto.getCity())
      .withState(customerAddressDto.getState())
      .withCommercialAddress(!isNull(isCommercialAddress) && isCommercialAddress)
      .withReference(customerAddressDto.getReference())
      .withCondominium(assembleCondominium(customerAddressDto.getCondominium()))
      .withApartment(assembleApartment(customerAddressDto.getApartment()))
      .build();
  }

  private void requiredFieldsVerification(CustomerAddressDto customerAddressDto) {
    LinkedFieldsValidation.linkedValidation()
      .ifEmpty(customerAddressDto.getCep(), "CEP")
      .ifEmpty(customerAddressDto.getStreet(), "Street")
      .ifEmpty(customerAddressDto.getNeighborhood(), "Neighborhood")
      .ifNull(customerAddressDto.getNumber(), "Number")
      .ifEmpty(customerAddressDto.getCity(), "City")
      .ifEmpty(customerAddressDto.getState(), "State")
      .ifEmpty(customerAddressDto.getReference(), "Reference")
      .ifAnyExceptionExistsThrow("are required to Address creation.");
  }

  private Apartment assembleApartment(ApartmentDto apartmentDto) {
    return isNull(apartmentDto)
      ? null
      : new Apartment(apartmentDto.getNumber(), apartmentDto.getBlock(), apartmentDto.getFloor(), apartmentDto.getObservation());
  }

  private Condominium assembleCondominium(CondominiumDto condominiumDto) {
    return isNull(condominiumDto)
      ? null
      : new Condominium(condominiumDto.getBlock(), condominiumDto.getHouseNumber(), condominiumDto.getObservation());
  }
}
