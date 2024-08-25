package com.ordermatic.app.security.infra.database.repositories;

import com.ordermatic.app.security.domain.repositories.CustomerUserRepository;
import com.ordermatic.app.security.domain.user.CustomerUser;
import com.ordermatic.app.security.domain.user.valueobjects.Email;
import com.ordermatic.app.security.domain.user.valueobjects.Phone;
import com.ordermatic.app.security.infra.database.collections.user.customer.CustomerUserCollection;
import com.ordermatic.shared.ddd.Mapper;
import com.ordermatic.shared.ddd.MongoAbstractRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class MongoCustomerUserRepository extends MongoAbstractRepository<CustomerUser, CustomerUserCollection> implements CustomerUserRepository {

  @Autowired
  public MongoCustomerUserRepository(MongoTemplate mongoTemplate, Mapper<CustomerUser, CustomerUserCollection> mapper) {
    super(mapper, mongoTemplate);
  }

  @Override
  public Optional<CustomerUser> findByEmailAndPhone(@NonNull Email email, @NonNull Phone phone) {
    var customer = mongoTemplate.findOne(
      query(where("email").is(email.getValue())
          .and("phone_number").is(phone.getValue())),
      CustomerUserCollection.class);

    return nonNull(customer) ? Optional.of(mapper.toDomain(customer)) : Optional.empty();
  }
}
