create schema if not exists security;

create table security.customer_user (
    id uuid primary key,
    name varchar(150) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    phone varchar(11) not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),

    constraint unique_email unique(email)
);

create table security.condominium (
    aggregate_id uuid not null,
    name varchar(150) not null,
    houseNumber int not null,
    observation varchar(255),

    create index idx_condominium_id on security.condominium(aggregate_id),
);

create table security.apartment (
    aggregate_id uuid not null,
    number varchar(10),
    block varchar(10),
    floor varchar(10),
    observation varchar(255),

    create index idx_apartment_id on security.apartment(aggregate_id),
);

create table security.address (
    aggregate_id uuid not null,
    street varchar(255) not null,
    city varchar(255) not null,
    state varchar(2) not null,
    number varchar(10) not null,
    cep number(8) not null,
    main boolean not null default false,
    reference varchar(255),
    apartment uuid,
    condominium uuid,

    constraint fk_apartment foreign key (apartment) references security.apartment(aggregate_id),
    constraint fk_condominium foreign key (condominium) references security.condominium(aggregate_id),
    create index idx_address_id on security.address(aggregate_id),
);
