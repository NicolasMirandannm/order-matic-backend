package com.ordermatic.shared.ddd;

import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.NonNull;

import java.util.Optional;

public abstract class Repository<Aggregate extends AggregateRoot, Infra extends InfraEntity> {

  private final Mapper<Aggregate, Infra> mapper;

  protected Repository(Mapper<Aggregate, Infra> mapper) {
    this.mapper = mapper;
  }

  public void save(@NonNull Aggregate aggregateRoot) {
    Infra infraEntity = mapper.toPersistence(aggregateRoot);
    saveInfraContext(infraEntity);
  }

  public Optional<Aggregate> findById(@NonNull UniqueIdentifier id) {
    Infra infraAggregate = findByIdInfraContext(id.getValue());
    return Optional.ofNullable(infraAggregate).map(mapper::toDomain);
  }

  public void delete(@NonNull Aggregate aggregateRoot) {
    deleteInfraContext(aggregateRoot.getIdValue());
  }

  protected abstract void saveInfraContext(Infra infraAggregate);
  protected abstract Infra findByIdInfraContext(String id);
  protected abstract void deleteInfraContext(String id);

}
