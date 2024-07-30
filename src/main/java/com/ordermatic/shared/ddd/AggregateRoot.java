package com.ordermatic.shared.ddd;

import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;

public abstract class AggregateRoot<T> extends DomainEntity<T> {
    protected AggregateRoot(UniqueIdentifier id) {
        super(id);
    }
}
