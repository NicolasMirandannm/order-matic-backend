package com.ordermatic.app.security.domain.user.factory;

import com.ordermatic.app.security.domain.user.factory.parameters.CustomerAddressFactoryParameter;
import com.ordermatic.app.security.domain.user.valueobjects.address.Address;
import com.ordermatic.app.security.domain.user.valueobjects.address.Apartment;
import com.ordermatic.app.security.domain.user.valueobjects.address.Condominium;
import com.ordermatic.shared.utilitaires.services.LinkedFieldsValidation;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class CustomerAddressFactory {
  public Address create(@NonNull CustomerAddressFactoryParameter customerAddressFactoryParameter) {
    requiredFieldsVerification(customerAddressFactoryParameter);

    var isCommercialAddress = customerAddressFactoryParameter.getIsCommercialAddress();
    return Address.anAddress()
      .withCep(customerAddressFactoryParameter.getCep())
      .withStreet(customerAddressFactoryParameter.getStreet())
      .withNumber(customerAddressFactoryParameter.getNumber())
      .withCity(customerAddressFactoryParameter.getCity())
      .withState(customerAddressFactoryParameter.getState())
      .withCommercialAddress(!isNull(isCommercialAddress) && isCommercialAddress)
      .withReference(customerAddressFactoryParameter.getReference())
      .withCondominium(assembleCondominium(customerAddressFactoryParameter.getCondominium()))
      .withApartment(assembleApartment(customerAddressFactoryParameter.getApartment()))
      .build();
  }

  private void requiredFieldsVerification(CustomerAddressFactoryParameter customerAddressFactoryParameter) {
    LinkedFieldsValidation.linkedValidation()
      .ifEmpty(customerAddressFactoryParameter.getCep(), "CEP")
      .ifEmpty(customerAddressFactoryParameter.getStreet(), "Street")
      .ifNull(customerAddressFactoryParameter.getNumber(), "Number")
      .ifEmpty(customerAddressFactoryParameter.getCity(), "City")
      .ifEmpty(customerAddressFactoryParameter.getState(), "State")
      .ifEmpty(customerAddressFactoryParameter.getReference(), "Reference")
      .ifAnyExceptionExistsThrow("are required to Address creation.");
  }

  private Apartment assembleApartment(CustomerAddressFactoryParameter.Apartment apartmentParam) {
    return isNull(apartmentParam)
      ? null
      : new Apartment(apartmentParam.getNumber(), apartmentParam.getBlock(), apartmentParam.getFloor(), apartmentParam.getObservation());
  }

  private Condominium assembleCondominium(CustomerAddressFactoryParameter.Condominium condominiumParam) {
    return isNull(condominiumParam)
      ? null
      : new Condominium(condominiumParam.getBlock(), condominiumParam.getHouseNumber(), condominiumParam.getObservation());
  }
}
