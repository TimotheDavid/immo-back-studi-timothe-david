DROP SCHEMA IF EXISTS immo cascade;

CREATE SCHEMA IF NOT EXISTS immo;
SET search_path = immo;


DROP TYPE IF EXISTS CIVILITY;

CREATE TYPE CIVILITY AS ENUM ('MONSIEUR','MADAME', 'AUTRE');
CREATE TABLE IF NOT EXISTS TENANT
(
    id           serial PRIMARY KEY NOT NULL UNIQUE,
    uuid         uuid               NOT NULL UNIQUE,
    firstname    varchar            NOT NULL,
    username     varchar            NOT NULL,
    birthdate    varchar            NOT NULL,
    birthplace   varchar            NOT NULL,
    email        varchar            NOT NULL UNIQUE,
    second_email varchar            NOT NULL,
    phone        varchar            NOT NULL,
    civility     CIVILITY           NOT NULL
);



CREATE TABLE IF NOT EXISTS APARTMENT
(
    id          serial PRIMARY KEY NOT NULL UNIQUE,
    uuid        uuid               NOT NULL UNIQUE,
    address     varchar            NOT NULL,
    complement  varchar            NOT NULL,
    city        varchar            NOT NULL,
    postal_code varchar            NOT NULL,
    charge      FLOAT DEFAULT 0,
    rent        FLOAT DEFAULT 0,
    deposit     FLOAT DEFAULT 0,
    deleted     bool  DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS RENT
(
    id              serial PRIMARY KEY NOT NULL UNIQUE,
    uuid            uuid               NOT NULL UNIQUE,
    rent            FLOAT DEFAULT 0,
    in_date         varchar,
    in_description  varchar,
    out_date        varchar,
    out_description varchar,
    deposit         FLOAT DEFAULT 0,
    agency_pourcent FLOAT DEFAULT 8,

    apartmentId     uuid,
    tenantId        uuid,
    FOREIGN KEY (apartmentId) REFERENCES APARTMENT (uuid),
    FOREIGN KEY (tenantId) REFERENCES TENANT (uuid)
);



DROP TYPE IF EXISTS TYPE_TO_PAY;

CREATE TYPE TYPE_TO_PAY AS ENUM ('CARTE', 'CHEQUE', 'ESPECE');

DROP TYPE IF EXISTS ORIGIN;

CREATE TYPE ORIGIN AS ENUM ('CAF', 'PROPRIETAIRE');

CREATE TABLE IF NOT EXISTS PAYMENT_RENT
(
    id        serial PRIMARY KEY NOT NULL UNIQUE,
    uuid      uuid               NOT NULL UNIQUE,

    rentId    uuid,
    paymentId uuid,

    FOREIGN KEY (rentId) REFERENCES RENT (uuid)
);

CREATE TABLE IF NOT EXISTS PAYMENT
(
    id           serial PRIMARY KEY NOT NULL UNIQUE,
    uuid         uuid               NOT NULL UNIQUE,
    amount       FLOAT              NOT NULL,
    date_payment varchar            NOT NULL,
    landlor_part FLOAT,
    agency_part  FLOAT,
    sens         BOOLEAN,
    type         TYPE_TO_PAY        NOT NULL,
    origin       ORIGIN             NOT NULL
);

CREATE TABLE IF NOT EXISTS USERS
(
    id       serial PRIMARY KEY NOT NULL UNIQUE,
    uuid     uuid               NOT NULL UNIQUE,
    name     varchar            NOT NULL,
    email    varchar            NOT NULL,
    password varchar            NOT NULL


);


CREATE TABLE IF NOT EXISTS AUTHENTICATION
(
    id      serial PRIMARY KEY NOT NULL UNIQUE,
    uuid    uuid               NOT NULL UNIQUE,
    token   varchar            NOT NULL,
    hash    varchar            NOT NULL,
    expires varchar            NOT NULL,

    userId  uuid,
    FOREIGN KEY (userId) REFERENCES USERS(uuid)

);



ALTER TABLE PAYMENT_RENT
    ADD FOREIGN KEY (paymentId) REFERENCES PAYMENT (uuid);