create schema if not exists security;

create table security.customer_user (
    id uuid primary key,
    name varchar(150) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    phone_number varchar(11) not null,
    version int not null default 0,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table security.condominium (
    id uuid primary key,
    name varchar(150) not null,
    house_number int not null,
    observation varchar(255),
    version int not null default 0,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);


create table security.apartment (
    id uuid primary key,
    number varchar(10) not null,
    block varchar(10) not null,
    floor varchar(10) not null,
    observation varchar(255),
    version int not null default 0,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table security.customer_address (
    id uuid primary key,
    customer_id uuid not null,
    street varchar(255) not null,
    city varchar(255) not null,
    state varchar(2) not null,
    number integer not null,
    cep varchar(8) not null,
    main boolean not null default false,
    reference varchar(255),
    apartment_id uuid,
    condominium_id uuid,
    version int not null default 0,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    constraint fk_apartment foreign key (apartment_id) references security.apartment(id),
    constraint fk_condominium foreign key (condominium_id) references security.condominium(id)
);

create unique index idx_customer_user_email on security.customer_user(email);

create index idx_address_id on security.customer_address(customer_id);
