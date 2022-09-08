\c immodev

DROP SCHEMA IF EXISTS immo cascade ;

CREATE SCHEMA IF NOT EXISTS immo;
SET search_path  = immo;
CREATE TABLE IF NOT EXISTS CIVILITY
(
    id       serial PRIMARY KEY NOT NULL UNIQUE ,
    uuid     uuid               NOT NULL UNIQUE ,
    civility varchar            NOT NULL
);
CREATE TABLE IF NOT EXISTS TENANT
(
    id           serial PRIMARY KEY NOT NULL UNIQUE ,
    uuid         uuid               NOT NULL UNIQUE ,
    firstname    varchar            NOT NULL,
    name         varchar            NOT NULL,
    birthdate    varchar            NOT NULL,
    birthplace   varchar            NOT NULL,
    email        varchar            NOT NULL UNIQUE ,
    second_email varchar            NOT NULL,
    phone        varchar            NOT NULL,

    civilityId   serial             NOT NULL,

    FOREIGN KEY (civilityId) REFERENCES CIVILITY (id)
);



CREATE TABLE IF NOT EXISTS APARTMENT
(
    id          serial PRIMARY KEY NOT NULL UNIQUE ,
    uuid        uuid               NOT NULL UNIQUE ,
    address     varchar            NOT NULL,
    complement  varchar            NOT NULL,
    city        varchar            NOT NULL,
    postal_code varchar            NOT NULL,
    charge      FLOAT DEFAULT 0,
    rent        FLOAT DEFAULT 0,
    deposit     FLOAT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS RENT
(
    id              serial PRIMARY KEY NOT NULL UNIQUE ,
    uuid            uuid               NOT NULL UNIQUE ,
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

CREATE TABLE IF NOT EXISTS ORIGIN
(
    id     serial PRIMARY KEY NOT NULL  UNIQUE ,
    uuid   uuid               NOT NULL UNIQUE ,
    origin varchar            NOT NULL
);

CREATE TABLE IF NOT EXISTS SENS_PAYMENT
(
    id     serial PRIMARY KEY NOT NULL UNIQUE ,
    uuid   uuid               NOT NULL UNIQUE ,
    sense  varchar            NOT NULL,
    action BOOLEAN
);

CREATE TYPE TYPE_TO_PAY AS ENUM ('Carte', 'Cheque', 'Espece');
CREATE TABLE IF NOT EXISTS TYPE_PAYMENT
(
    id   serial PRIMARY KEY NOT NULL UNIQUE ,
    uuid uuid               NOT NULL UNIQUE ,
    type TYPE_TO_PAY,

    sensPaymentId uuid,

    FOREIGN KEY (sensPaymentId) REFERENCES SENS_PAYMENT(uuid)
);

CREATE TABLE IF NOT EXISTS PAYMENT_RENT
(
    id        serial PRIMARY KEY NOT NULL UNIQUE ,
    uuid      uuid               NOT NULL UNIQUE ,

    rentId    uuid,
    paymentId uuid,

    FOREIGN KEY (rentId) REFERENCES RENT (uuid)
);

CREATE TABLE IF NOT EXISTS PAYMENT
(
    id            serial PRIMARY KEY NOT NULL UNIQUE ,
    uuid          uuid               NOT NULL UNIQUE ,
    amount        FLOAT              NOT NULL,
    date_payment  varchar            NOT NULL,
    landlor_part  FLOAT,
    agency_part   FLOAT,

    originId      uuid             NOT NULL,
    typePaymentId uuid             NOT NULL,
    paymentRentId uuid,

    FOREIGN KEY (originId) REFERENCES ORIGIN (uuid),
    FOREIGN KEY (typePaymentId) REFERENCES TYPE_PAYMENT (uuid),
    FOREIGN KEY (paymentRentId) REFERENCES PAYMENT_RENT (uuid)
);





CREATE TABLE IF NOT EXISTS AUTHENTICATION
(
    id      serial PRIMARY KEY NOT NULL UNIQUE ,
    uuid    uuid               NOT NULL UNIQUE ,
    token   varchar            NOT NULL,
    hash    varchar            NOT NULL,
    expires varchar            NOT NULL
);

CREATE TABLE IF NOT EXISTS USERS
(
    id               serial PRIMARY KEY NOT NULL UNIQUE ,
    uuid             uuid               NOT NULL UNIQUE ,
    name             varchar            NOT NULL,
    email            varchar            NOT NULL,
    password         varchar            NOT NULL,

    authenticationId uuid,
    FOREIGN KEY (authenticationId) REFERENCES AUTHENTICATION (uuid)
);

ALTER TABLE PAYMENT_RENT
ADD FOREIGN KEY (paymentId) REFERENCES PAYMENT(uuid);