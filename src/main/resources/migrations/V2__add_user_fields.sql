CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

alter table "evictions_user"
    add column "password" character varying(255);

CREATE TABLE "public"."user_roles"
(
    "user_id" character varying(255) not null,
    "roles"   character varying(255),
    CONSTRAINT "fk_userid" FOREIGN KEY (user_id) REFERENCES evictions_user (user_id) NOT DEFERRABLE
) with (oids = false);