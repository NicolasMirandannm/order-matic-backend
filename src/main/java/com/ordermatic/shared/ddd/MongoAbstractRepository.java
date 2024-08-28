package com.ordermatic.shared.ddd;

import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.NonNull;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;


public abstract class MongoAbstractRepository<Aggregate extends AggregateRoot, Infra extends InfraEntity> implements AggregateRepository<Aggregate> {

  protected final Mapper<Aggregate, Infra> mapper;
  protected final MongoTemplate mongoTemplate;

  protected MongoAbstractRepository(Mapper<Aggregate, Infra> mapper, MongoTemplate mongoTemplate) {
    this.mapper = mapper;
    this.mongoTemplate = mongoTemplate;
  }

  public void save(@NonNull Aggregate aggregateRoot) {
    Infra infraEntity = mapper.toPersistence(aggregateRoot);
    mongoTemplate.save(infraEntity);
  }

  public Optional<Aggregate> findById(@NonNull UniqueIdentifier id) {
    Infra infraAggregate = mongoTemplate.findById(id.getValue(), getInfraClass());
    return Optional.ofNullable(infraAggregate).map(mapper::toDomain);
  }

  public void delete(@NonNull Aggregate aggregateRoot) {
    mongoTemplate.remove(mapper.toPersistence(aggregateRoot));
  }

  public void deleteAll() {
    mongoTemplate.remove(getInfraClass()).all();
  }

  private Class<Infra> getInfraClass() {
    return (Class<Infra>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
  }

}
