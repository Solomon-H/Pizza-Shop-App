BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS pizza (
    pizza_id SERIAL PRIMARY KEY,
    sauce VARCHAR(255),
    size VARCHAR(255),
    topping VARCHAR(255),
    price NUMERIC
);


CREATE TABLE IF NOT EXISTS customer (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255),
    address VARCHAR(255),
    phone_number VARCHAR(20)
);


INSERT INTO pizza (sauce, size, topping, price)
VALUES
    ('Tomato', 'Large', 'Pepperoni', 12.99),
    ('BBQ', 'Medium', 'Chicken', 10.99),
    ('Alfredo', 'Small', 'Mushrooms', 8.99);


INSERT INTO customer (full_name, address, phone_number)
VALUES
    ('Elroi Sele', '1276 Main St', '614-777-7777'),
    ('Eliana Mana', '494 S Hamilton Rd', '614-888-8888'),
    ('Peniel Fili', '789 E Broad St', '614-555-5555');
	
COMMIT;	
