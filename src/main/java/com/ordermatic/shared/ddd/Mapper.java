package com.ordermatic.shared.ddd;

import lombok.NonNull;

public interface Mapper <Domain extends DomainEntity, Infra extends InfraEntity> {
    Domain toDomain(@NonNull Infra infra);
    Infra toPersistence(@NonNull Domain domain);
}
