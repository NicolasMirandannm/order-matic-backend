package com.ordermatic.shared.ddd;

import com.ordermatic.shared.utilitaires.valueobjs.UniqueIdentifier;
import lombok.experimental.SuperBuilder;

@SuperBuilder(setterPrefix = "with")
public abstract class AggregateRoot extends DomainEntity {
    protected AggregateRoot(UniqueIdentifier id) {
        super(id);
    }
}
