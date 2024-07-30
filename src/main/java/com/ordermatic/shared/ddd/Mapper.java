package com.ordermatic.shared.ddd;

public interface Mapper <Domain extends DomainEntity<?>, Infra extends InfraEntity<?>> {
    Domain toDomain(Infra infra);
    Infra toPersistence(Domain domain);
}
