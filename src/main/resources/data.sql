CREATE TABLE product (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100),
    price FLOAT,
    category VARCHAR(100) NOT NULL
    );

INSERT INTO product (id, name, description, price, category)
VALUES
('ec541e16-0019-41fd-9b3d-6c2229086c0a', 'Lego Mario', 'Lego Game', 12.99, 'Toys'),
('84b11e0d-7282-44dd-8e22-b6d26086d25b', 'Lego HeMan', 'Lego Game', 52.99, 'Toys');