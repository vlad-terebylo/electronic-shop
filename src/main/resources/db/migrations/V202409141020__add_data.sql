INSERT INTO item_type (title)
VALUES ('Motherboard');

INSERT INTO item_type (title)
VALUES ('Computer');

INSERT INTO item (title, price, producing_year, manufacturer, quantity, item_type_id)
VALUES ('MacBook Pro', 750, '2024.01.01', 'Apple', 50, 2);

INSERT INTO user_roles (title)
VALUES ('Admin');

INSERT INTO "user" (name, login, password, role_id)
VALUES ('Vlad', 'vlad_tvo_shop', 'R$_pin/*', 1);
