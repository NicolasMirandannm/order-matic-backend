package com.ordermatic.app.security.application.customer.services;

import com.ordermatic.app.security.application.customer.dto.AddressDto;
import com.ordermatic.app.security.application.customer.dto.ApartmentDto;
import com.ordermatic.app.security.application.customer.dto.CondominiumDto;
import com.ordermatic.app.security.domain.exceptions.UserNotFoundException;
import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.factory.CustomerAddressFactory;
import com.ordermatic.app.security.domain.user.factory.parameters.CustomerAddressFactoryParameter;
import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class CustomerAddressCreationService {

  private final CustomerUserRepository customerUserRepository;
  private final CustomerAddressFactory customerAddressFactory;

  @Autowired
  public CustomerAddressCreationService(CustomerUserRepository customerUserRepository, CustomerAddressFactory customerAddressFactory) {
    this.customerUserRepository = customerUserRepository;
    this.customerAddressFactory = customerAddressFactory;
  }

  public void execute(String customerId, AddressDto addressDto) {
    var customerIdentifier = new UniqueIdentifier(customerId);
    CustomerUser customer = customerUserRepository.findById(customerIdentifier)
      .orElseThrow(() -> new UserNotFoundException(customerIdentifier));

    var customerAddressFactoryParameters = mapAddressParameters(addressDto);
    var addressCreated = customerAddressFactory.create(customerAddressFactoryParameters);
    customer.addNewAddress(addressCreated, addressDto.getIsMain());
    customerUserRepository.save(customer);
  }

  private CustomerAddressFactoryParameter mapAddressParameters(AddressDto addressDto) {
    return CustomerAddressFactoryParameter.builder()
      .street(addressDto.getStreet())
      .number(addressDto.getNumber())
      .neighborhood(addressDto.getNeighborhood())
      .city(addressDto.getCity())
      .state(addressDto.getState())
      .isCommercialAddress(addressDto.getIsCommercialAddress())
      .cep(addressDto.getCep())
      .reference(addressDto.getReference())
      .condominium(mapCondominiumParameter(addressDto.getCondominium()))
      .apartment(mapApartmentParameter(addressDto.getApartment()))
      .build();
  }

  private CustomerAddressFactoryParameter.Apartment mapApartmentParameter(ApartmentDto apartmentDto) {
    if (isNull(apartmentDto)) return null;
    return CustomerAddressFactoryParameter.Apartment.builder()
      .number(apartmentDto.getNumber())
      .block(apartmentDto.getBlock())
      .floor(apartmentDto.getFloor())
      .observation(apartmentDto.getObservation())
      .build();
  }

  private CustomerAddressFactoryParameter.Condominium mapCondominiumParameter(CondominiumDto condominiumDto) {
    if (isNull(condominiumDto)) return null;
    return CustomerAddressFactoryParameter.Condominium.builder()
      .block(condominiumDto.getBlock())
      .houseNumber(condominiumDto.getHouseNumber())
      .observation(condominiumDto.getObservation())
      .build();
  }
}
