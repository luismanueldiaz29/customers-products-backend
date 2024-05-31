create table customer
(
    id                  bigint not null
        primary key,
    born_date           date,
    created_at          timestamp(6),
    email               varchar(255),
    identification      varchar(255),
    identification_type varchar(255),
    last_name           varchar(255),
    name                varchar(255),
    updated_at          timestamp(6)
        constraint uk_q6ux6is14rkik374noo9xwo5
            unique
);

alter table customer
    owner to root;

create table product
(
    id             bigint  not null
        primary key,
    account_number varchar(255),
    account_type   varchar(255),
    amount         numeric(38, 2),
    created_at     timestamp(6),
    gmf_exempt     boolean not null,
    status         varchar(255),
    updated_at     timestamp(6),
    customer_id    bigint
        constraint fkj80n6400wnfqrt86qimf9k6ys
            references customer
);

alter table product
    owner to root;

alter table customer
    add constraint fknnwpo0lfq4xai1rs6887sx02k
        foreign key (updated_at) references product;

create unique index uk_h5t1idb3gcg6x08fiwmbal5de
    on product ();

alter table product
    add constraint uk_h5t1idb3gcg6x08fiwmbal5de
        unique (updated_at);

create table transaction
(
    id                     bigserial
        primary key,
    amount                 numeric(38, 2),
    type                   varchar(255)
        constraint transaction_type_check
            check ((type)::text = ANY
        ((ARRAY ['CONSIGNATION'::character varying, 'WITHDRAWAL'::character varying, 'TRANSFER'::character varying])::text[])),
    destination_account_id bigint
        constraint fkfge7hq0ogjf1j9q1aucnrq5on
            references product,
    source_account_id      bigint
        constraint fk6mxfbpvkqrf60ujydb8n50wfb
            references product
);

alter table transaction
    owner to root;

create table account
(
    id             bigint  not null
        primary key,
    account_number varchar(255),
    account_type   varchar(255),
    amount         numeric(38, 2),
    created_at     timestamp(6),
    gmf_exempt     boolean not null,
    status         varchar(255),
    updated_at     timestamp(6),
    customer_id    bigint
        constraint uk_q6ux6is14rkik374noo9xwo5
            unique
        constraint fknnwpo0lfq4xai1rs6887sx02k
            references customer
);

alter table account
    owner to root;

