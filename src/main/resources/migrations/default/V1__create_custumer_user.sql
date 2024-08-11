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