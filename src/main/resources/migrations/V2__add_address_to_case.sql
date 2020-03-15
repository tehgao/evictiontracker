alter table public.case_entity
    add column property_id uuid,
    add constraint FK_property_key foreign key (property_id) references public.address_entity