package com.ordermatic.shared.ddd;

import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;

import java.util.Optional;

public interface AggregateRepository<T extends AggregateRoot> {
  void save(T aggregateRoot);

  Optional<T> findById(UniqueIdentifier id);

  void delete(T aggregateRoot);

  void deleteAll();
}
