CREATE TABLE "special_offers"
(
    "id"          BIGSERIAL PRIMARY KEY,
    "name"        VARCHAR,
    "start_date"  DATE,
    "end_date"    DATE,
    "description" VARCHAR
);


CREATE TABLE "blog_posts"
(
    "id"               BIGSERIAL PRIMARY KEY,
    "heading"          VARCHAR,
    "content"          VARCHAR,
    "publication_date" TIMESTAMPTZ
);

create table "products"
(
    id          bigserial
        primary key,
    name        varchar,
    description varchar,
    price       numeric(8, 2),
    status      varchar,
    brand_id    bigint
        references brands,
    img_src     varchar
);

create table brands
(
    id          bigserial
        primary key,
    name        varchar,
    country     varchar,
    description varchar,
    website     varchar
);


CREATE TABLE "users"
(
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR,
    date_of_birth DATE,
    password      VARCHAR
);