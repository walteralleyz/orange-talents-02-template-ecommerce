INSERT INTO categories(id, name) VALUES (1, 'comida');

INSERT INTO users(id, login, password, created_at) VALUES (1, 'user@mail.com', '$2a$10$3meVxJDehPp1d/xwGw0ER.HbrEMol7c9Fx.7ESZVdVM3dE4DRwuD6', '2021-01-01');
INSERT INTO users(id, login, password, created_at) VALUES (2, 'guest@mail.com', '$2a$10$3meVxJDehPp1d/xwGw0ER.HbrEMol7c9Fx.7ESZVdVM3dE4DRwuD6', '2021-01-01');

INSERT INTO products(id, name, price, quantity, description, category_id, user_id, created_at)
VALUES (1, 'pepino', 2.99, 10, 'legume', 1, 1, '2021-01-01');

INSERT INTO details(id, title, text, product_id) VALUES (1, 'cor', 'verde', 1);
INSERT INTO details(id, title, text, product_id) VALUES (2, 'tamanho', 'grande', 1);
INSERT INTO details(id, title, text, product_id) VALUES (3, 'tipo', 'abacate', 1);

INSERT INTO checkout(id, product_id, product_quantity, client_id, status, payment_type)
VALUES (1, 1, 5, 2, 'INICIADA', 'PAYPAL');

UPDATE products SET quantity = 5 WHERE id = 1;