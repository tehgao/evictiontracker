create table public.address_entity
(
    id                       uuid not null,
    created_date             timestamp,
    last_modified_date       timestamp,
    city                     varchar(255),
    latitude                 numeric(19, 2),
    longitude                numeric(19, 2),
    neighborhood             varchar(255),
    state                    varchar(255),
    street_address           varchar(255),
    street_address_secondary varchar(255),
    zip_code                 varchar(255),
    created_by_user_id       varchar(255),
    last_modified_by_user_id varchar(255),
    primary key (id)
);
create table public.attorney_entity
(
    id                       uuid not null,
    created_date             timestamp,
    last_modified_date       timestamp,
    name                     varchar(255),
    created_by_user_id       varchar(255),
    last_modified_by_user_id varchar(255),
    primary key (id)
);
create table public.case_entity
(
    id                       uuid not null,
    created_date             timestamp,
    last_modified_date       timestamp,
    case_number              varchar(255),
    file_date                date,
    created_by_user_id       varchar(255),
    last_modified_by_user_id varchar(255),
    primary key (id)
);
create table public.case_entity_defendants
(
    is_defendant_of_id uuid not null,
    defendants_id      uuid not null
);
create table public.case_entity_events
(
    case_entity_id uuid not null,
    events_id      uuid not null
);
create table public.case_entity_plaintiffs
(
    is_plaintiff_of_id uuid not null,
    plaintiffs_id      uuid not null
);
create table public.event_entity
(
    id                       uuid    not null,
    created_date             timestamp,
    last_modified_date       timestamp,
    date_time                timestamp,
    event_type               int4,
    pro_se                   boolean not null,
    created_by_user_id       varchar(255),
    last_modified_by_user_id varchar(255),
    primary key (id)
);
create table public.evictions_user
(
    user_id varchar(255) not null,
    primary key (user_id)
);
create table public.party_entity
(
    id                       uuid not null,
    created_date             timestamp,
    last_modified_date       timestamp,
    name                     varchar(255),
    created_by_user_id       varchar(255),
    last_modified_by_user_id varchar(255),
    address_id               uuid,
    attorney_id              uuid,
    primary key (id)
);
alter table public.case_entity
    drop constraint if exists UK_bcn58de4xlby2456wpf30c2f;
alter table public.case_entity
    add constraint UK_bcn58de4xlby2456wpf30c2f unique (case_number);
alter table public.case_entity_events
    drop constraint if exists UK_jaokkwoye39uulvyaon48j983;
alter table public.case_entity_events
    add constraint UK_jaokkwoye39uulvyaon48j983 unique (events_id);
alter table public.party_entity
    drop constraint if exists UK_kh29n4a2lp4y8u0w8119hl02y;
alter table public.party_entity
    add constraint UK_kh29n4a2lp4y8u0w8119hl02y unique (name);
alter table public.address_entity
    add constraint FKc6gw42h0frnp52se4a1w1xef9 foreign key (created_by_user_id) references public.evictions_user;
alter table public.address_entity
    add constraint FKgorye8dxkkxqkt76vff5mwfpk foreign key (last_modified_by_user_id) references public.evictions_user;
alter table public.attorney_entity
    add constraint FKg5p8wpsalo8177xibhofmg0so foreign key (created_by_user_id) references public.evictions_user;
alter table public.attorney_entity
    add constraint FK39u5gdi81u52py4j33lc9o25r foreign key (last_modified_by_user_id) references public.evictions_user;
alter table public.case_entity
    add constraint FK9os7lxvix0bdf9xs7k969owxl foreign key (created_by_user_id) references public.evictions_user;
alter table public.case_entity
    add constraint FKo4pk32c9ebum5vjfra2ipt024 foreign key (last_modified_by_user_id) references public.evictions_user;
alter table public.case_entity_defendants
    add constraint FK74ffhi1knvlevihy434ttqw9u foreign key (defendants_id) references public.party_entity;
alter table public.case_entity_defendants
    add constraint FK89jbpcgkg8sjmodtywdegw8l4 foreign key (is_defendant_of_id) references public.case_entity;
alter table public.case_entity_events
    add constraint FK58poo21r6m9iubjys156reb15 foreign key (events_id) references public.event_entity;
alter table public.case_entity_events
    add constraint FKfbv6b9413lpv4xvxpv0fqhhvj foreign key (case_entity_id) references public.case_entity;
alter table public.case_entity_plaintiffs
    add constraint FKj4gj04c6jm7ntxhs9yccbqlhk foreign key (plaintiffs_id) references public.party_entity;
alter table public.case_entity_plaintiffs
    add constraint FKgob6c5vq2ljo4ubydtuxgxu3b foreign key (is_plaintiff_of_id) references public.case_entity;
alter table public.event_entity
    add constraint FKep2o90etyl8tuuqhir8wp47qs foreign key (created_by_user_id) references public.evictions_user;
alter table public.event_entity
    add constraint FKtis6v9gx6m48w6vmw5ix0obgm foreign key (last_modified_by_user_id) references public.evictions_user;
alter table public.party_entity
    add constraint FK3o4ifiyyy52vbkd6mjuds1ruh foreign key (created_by_user_id) references public.evictions_user;
alter table public.party_entity
    add constraint FKbnnr3uf2tr20juepomwqrtv8f foreign key (last_modified_by_user_id) references public.evictions_user;
alter table public.party_entity
    add constraint FKqd32hd4df92rwtcfceewu7ro9 foreign key (address_id) references public.address_entity;
alter table public.party_entity
    add constraint FKpcb1kvwk2hsa4ik8aw6t0ib3h foreign key (attorney_id) references public.attorney_entity;
