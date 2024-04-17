CREATE TABLE "special_offers"(
                                 "id" BIGSERIAL PRIMARY KEY,
                                 "name" VARCHAR,
                                 "start_date" DATE,
                                 "end_date" DATE,
                                 "description" VARCHAR
);


CREATE TABLE "blog_posts"(
                             "id" BIGSERIAL PRIMARY KEY,
                             "heading" VARCHAR,
                             "content" VARCHAR,
                             "publication_date" TIMESTAMPTZ
);


CREATE TABLE "categories"(
                             "id" BIGSERIAL PRIMARY KEY,
                             "name" VARCHAR
);


CREATE TABLE "statuses"(
                           "id" BIGSERIAL PRIMARY KEY,
                           "name" VARCHAR
);

CREATE TABLE "products"(
                           "id" BIGSERIAL PRIMARY KEY,
                           "name" VARCHAR,
                           "description" VARCHAR,
                           "price" DECIMAL(8, 2),
                           "status_id" BIGINT REFERENCES statuses(id),
                           "category_id" BIGINT REFERENCES categories(id)
);