create table public.upload_task
(
    id          uuid not null,
    started_on  timestamp,
    finished_on timestamp,
    primary key (id)
);