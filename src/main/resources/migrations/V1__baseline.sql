-- Adminer 4.7.6 PostgreSQL dump

DROP TABLE IF EXISTS "address_entity";
CREATE TABLE "public"."address_entity"
(
    "id"                       uuid NOT NULL,
    "created_date"             timestamp,
    "last_modified_date"       timestamp,
    "city"                     character varying(255),
    "latitude"                 numeric(19, 2),
    "longitude"                numeric(19, 2),
    "neighborhood"             character varying(255),
    "state"                    character varying(255),
    "street_address"           character varying(255),
    "street_address_secondary" character varying(255),
    "zip_code"                 character varying(255),
    "created_by_user_id"       character varying(255),
    "last_modified_by_user_id" character varying(255),
    CONSTRAINT "address_entity_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "fkc6gw42h0frnp52se4a1w1xef9" FOREIGN KEY (created_by_user_id) REFERENCES evictions_user (user_id) NOT DEFERRABLE,
    CONSTRAINT "fkgorye8dxkkxqkt76vff5mwfpk" FOREIGN KEY (last_modified_by_user_id) REFERENCES evictions_user (user_id) NOT DEFERRABLE
) WITH (oids = false);


DROP TABLE IF EXISTS "attorney_entity";
CREATE TABLE "public"."attorney_entity"
(
    "id"                       uuid NOT NULL,
    "created_date"             timestamp,
    "last_modified_date"       timestamp,
    "name"                     character varying(255),
    "created_by_user_id"       character varying(255),
    "last_modified_by_user_id" character varying(255),
    CONSTRAINT "attorney_entity_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "fk39u5gdi81u52py4j33lc9o25r" FOREIGN KEY (last_modified_by_user_id) REFERENCES evictions_user (user_id) NOT DEFERRABLE,
    CONSTRAINT "fkg5p8wpsalo8177xibhofmg0so" FOREIGN KEY (created_by_user_id) REFERENCES evictions_user (user_id) NOT DEFERRABLE
) WITH (oids = false);


DROP TABLE IF EXISTS "case_entity";
CREATE TABLE "public"."case_entity"
(
    "id"                       uuid NOT NULL,
    "created_date"             timestamp,
    "last_modified_date"       timestamp,
    "case_number"              character varying(255),
    "file_date"                date,
    "created_by_user_id"       character varying(255),
    "last_modified_by_user_id" character varying(255),
    CONSTRAINT "case_entity_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk_bcn58de4xlby2456wpf30c2f" UNIQUE ("case_number"),
    CONSTRAINT "fk9os7lxvix0bdf9xs7k969owxl" FOREIGN KEY (created_by_user_id) REFERENCES evictions_user (user_id) NOT DEFERRABLE,
    CONSTRAINT "fko4pk32c9ebum5vjfra2ipt024" FOREIGN KEY (last_modified_by_user_id) REFERENCES evictions_user (user_id) NOT DEFERRABLE
) WITH (oids = false);


DROP TABLE IF EXISTS "case_entity_defendants";
CREATE TABLE "public"."case_entity_defendants"
(
    "is_defendant_of_id" uuid NOT NULL,
    "defendants_id"      uuid NOT NULL,
    CONSTRAINT "fk74ffhi1knvlevihy434ttqw9u" FOREIGN KEY (defendants_id) REFERENCES party_entity (id) NOT DEFERRABLE,
    CONSTRAINT "fk89jbpcgkg8sjmodtywdegw8l4" FOREIGN KEY (is_defendant_of_id) REFERENCES case_entity (id) NOT DEFERRABLE
) WITH (oids = false);


DROP TABLE IF EXISTS "case_entity_events";
CREATE TABLE "public"."case_entity_events"
(
    "case_entity_id" uuid NOT NULL,
    "events_id"      uuid NOT NULL,
    CONSTRAINT "uk_jaokkwoye39uulvyaon48j983" UNIQUE ("events_id"),
    CONSTRAINT "fk58poo21r6m9iubjys156reb15" FOREIGN KEY (events_id) REFERENCES event_entity (id) NOT DEFERRABLE,
    CONSTRAINT "fkfbv6b9413lpv4xvxpv0fqhhvj" FOREIGN KEY (case_entity_id) REFERENCES case_entity (id) NOT DEFERRABLE
) WITH (oids = false);


DROP TABLE IF EXISTS "case_entity_plaintiffs";
CREATE TABLE "public"."case_entity_plaintiffs"
(
    "is_plaintiff_of_id" uuid NOT NULL,
    "plaintiffs_id"      uuid NOT NULL,
    CONSTRAINT "fkgob6c5vq2ljo4ubydtuxgxu3b" FOREIGN KEY (is_plaintiff_of_id) REFERENCES case_entity (id) NOT DEFERRABLE,
    CONSTRAINT "fkj4gj04c6jm7ntxhs9yccbqlhk" FOREIGN KEY (plaintiffs_id) REFERENCES party_entity (id) NOT DEFERRABLE
) WITH (oids = false);

DROP TABLE IF EXISTS "event_entity";
CREATE TABLE "public"."event_entity"
(
    "id"                       uuid    NOT NULL,
    "created_date"             timestamp,
    "last_modified_date"       timestamp,
    "date_time"                timestamp,
    "event_type"               integer,
    "pro_se"                   boolean NOT NULL,
    "created_by_user_id"       character varying(255),
    "last_modified_by_user_id" character varying(255),
    CONSTRAINT "event_entity_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "fkep2o90etyl8tuuqhir8wp47qs" FOREIGN KEY (created_by_user_id) REFERENCES evictions_user (user_id) NOT DEFERRABLE,
    CONSTRAINT "fktis6v9gx6m48w6vmw5ix0obgm" FOREIGN KEY (last_modified_by_user_id) REFERENCES evictions_user (user_id) NOT DEFERRABLE
) WITH (oids = false);


DROP TABLE IF EXISTS "evictions_user";
CREATE TABLE "public"."evictions_user"
(
    "user_id" character varying(255) NOT NULL,
    CONSTRAINT "evictions_user_pkey" PRIMARY KEY ("user_id")
) WITH (oids = false);


DROP TABLE IF EXISTS "flyway_schema_history";
CREATE TABLE "public"."flyway_schema_history"
(
    "installed_rank" integer                 NOT NULL,
    "version"        character varying(50),
    "description"    character varying(200)  NOT NULL,
    "type"           character varying(20)   NOT NULL,
    "script"         character varying(1000) NOT NULL,
    "checksum"       integer,
    "installed_by"   character varying(100)  NOT NULL,
    "installed_on"   timestamp DEFAULT now() NOT NULL,
    "execution_time" integer                 NOT NULL,
    "success"        boolean                 NOT NULL,
    CONSTRAINT "flyway_schema_history_pk" PRIMARY KEY ("installed_rank")
) WITH (oids = false);

CREATE INDEX "flyway_schema_history_s_idx" ON "public"."flyway_schema_history" USING btree ("success");


DROP TABLE IF EXISTS "party_entity";
CREATE TABLE "public"."party_entity"
(
    "id"                       uuid NOT NULL,
    "created_date"             timestamp,
    "last_modified_date"       timestamp,
    "name"                     character varying(255),
    "created_by_user_id"       character varying(255),
    "last_modified_by_user_id" character varying(255),
    "address_id"               uuid,
    "attorney_id"              uuid,
    CONSTRAINT "party_entity_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk_kh29n4a2lp4y8u0w8119hl02y" UNIQUE ("name"),
    CONSTRAINT "fk3o4ifiyyy52vbkd6mjuds1ruh" FOREIGN KEY (created_by_user_id) REFERENCES evictions_user (user_id) NOT DEFERRABLE,
    CONSTRAINT "fkbnnr3uf2tr20juepomwqrtv8f" FOREIGN KEY (last_modified_by_user_id) REFERENCES evictions_user (user_id) NOT DEFERRABLE,
    CONSTRAINT "fkpcb1kvwk2hsa4ik8aw6t0ib3h" FOREIGN KEY (attorney_id) REFERENCES attorney_entity (id) NOT DEFERRABLE,
    CONSTRAINT "fkqd32hd4df92rwtcfceewu7ro9" FOREIGN KEY (address_id) REFERENCES address_entity (id) NOT DEFERRABLE
) WITH (oids = false);


-- 2020-03-11 01:28:19.559444+00