CREATE TABLE IF NOT EXISTS item_type
(
    id    INTEGER       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255)  NOT NULL
);

CREATE TABLE IF NOT EXISTS item
(
    id             INTEGER         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title          VARCHAR(255)    NOT NULL,
    price          DECIMAL(12, 2)  NOT NULL,
    producing_year DATE            NOT NULL,
    manufacturer   VARCHAR(255)    NOT NULL,
    quantity       INTEGER         NOT NULL,
    item_type_id   INTEGER         NOT NULL,
    CONSTRAINT fk_item_item_type FOREIGN KEY (item_type_id) REFERENCES item_type (id)
);

CREATE TABLE IF NOT EXISTS customer_purchase
(
    id          INTEGER       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email       VARCHAR(255)  NOT NULL,
    card_number VARCHAR(255)  NOT NULL
);

CREATE TABLE IF NOT EXISTS purchase_item
(
    id          INTEGER  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    purchase_id INTEGER  NOT NULL,
    item_id     INTEGER  NOT NULL,
    CONSTRAINT fk_purchase_item_purchase FOREIGN KEY (purchase_id) REFERENCES customer_purchase (id),
    CONSTRAINT fk_purchase_item_item     FOREIGN KEY (item_id)     REFERENCES item (id)
);