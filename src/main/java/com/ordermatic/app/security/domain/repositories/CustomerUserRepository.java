package com.ordermatic.app.security.domain.repositories;

import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.valueobjects.Email;
import com.ordermatic.app.security.domain.user.valueobjects.Phone;
import com.ordermatic.shared.ddd.AggregateRepository;
import lombok.NonNull;

import java.util.Optional;

public interface CustomerUserRepository extends AggregateRepository<CustomerUser> {

  Optional<CustomerUser> findByEmailAndPhone(@NonNull Email email,@NonNull Phone phone);

}
