ALTER TABLE devices ADD CONSTRAINT name_unique UNIQUE(name);

create table authors
(
    id        serial
        primary key,
    full_name varchar
);

create table public.devices
(
    id      serial
        primary key,
    name    varchar
        constraint name_unique
            unique,
    details varchar,
    price   double precision
);

create table public.newscards
(
    id            serial
        primary key,
    header        varchar,
    content       varchar,
    creation_date timestamp with time zone default now(),
    author_id     integer
        references public.authors
);

ALTER TABLE newscards
    DROP CONSTRAINT IF EXISTS newscards_author_id_fkey;

ALTER TABLE newscards
    ADD CONSTRAINT newscards_author_id_fkey
        FOREIGN KEY (author_id)
            REFERENCES authors (id)
            ON DELETE CASCADE;

