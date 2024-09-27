CREATE TABLE IF NOT EXISTS item_type
(
    id    INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    title VARCHAR                                          NOT NULL
);

CREATE TABLE IF NOT EXISTS item
(
    id             INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    title          VARCHAR                                          NOT NULL,
    price          FLOAT                                            NOT NULL,
    producing_year DATE                                             NOT NULL,
    manufacturer   VARCHAR                                          NOT NULL,
    quantity       INTEGER                                          NOT NULL,
    item_type_id   INTEGER REFERENCES item_type (id)                NOT NULL
);

CREATE TABLE IF NOT EXISTS customer_purchase
(
    id          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    email       VARCHAR                                          NOT NULL,
    card_number VARCHAR                                          NOT NULL
);

CREATE TABLE IF NOT EXISTS purchase_item
(
    id          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    purchase_id INTEGER REFERENCES customer_purchase (id)        NOT NULL,
    item_id     INTEGER REFERENCES item (id)                     NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles
(
    id    INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    title VARCHAR                                          NOT NULL
);

CREATE TABLE IF NOT EXISTS "user"
(
    id       INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    name     VARCHAR                                          NOT NULL,
    login    VARCHAR                                          NOT NULL,
    password VARCHAR                                          NOT NULL,
    role_id  INTEGER REFERENCES user_roles (id)               NOT NULL
);

