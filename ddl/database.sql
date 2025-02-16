CREATE TABLE customers (
                           customer_id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) NOT NULL UNIQUE,
                           phone VARCHAR(20),
                           address TEXT
);

CREATE TABLE products (
                          product_id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          price DECIMAL(10, 2) NOT NULL,
                          stock_quantity INT NOT NULL
);

CREATE TABLE orders (
                        order_id SERIAL PRIMARY KEY,
                        customer_id INT NOT NULL,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        total DECIMAL(10, 2) NOT NULL,
                        status VARCHAR(50) DEFAULT 'PENDING',  -- PENDING, PAID, SHIPPED, CANCELED
                        FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE order_items (
                             order_item_id SERIAL PRIMARY KEY,
                             order_id INT NOT NULL,
                             product_id INT NOT NULL,
                             quantity INT NOT NULL,
                             unit_price DECIMAL(10, 2) NOT NULL,
                             total DECIMAL(10, 2) NOT NULL,
                             FOREIGN KEY (order_id) REFERENCES orders(order_id),
                             FOREIGN KEY (product_id) REFERENCES products(product_id)
);


CREATE TABLE payments (
                          payment_id SERIAL PRIMARY KEY,
                          order_id INT NOT NULL,
                          payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          payment_method VARCHAR(50),  -- e.g., CREDIT_CARD, PAYPAL, BANK_TRANSFER
                          amount DECIMAL(10, 2) NOT NULL,
                          status VARCHAR(50) DEFAULT 'PENDING',  -- PENDING, COMPLETED, FAILED
                          FOREIGN KEY (order_id) REFERENCES orders(order_id)
);


CREATE TABLE stock_updates (
                               stock_update_id SERIAL PRIMARY KEY,
                               product_id INT NOT NULL,
                               change_quantity INT NOT NULL,  -- Quantidade comprada ou retornada
                               update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (product_id) REFERENCES products(product_id)
);


INSERT INTO customers (name, email, phone, address)
VALUES ('John Doe', 'john.doe@example.com', '123456789', '123 Main St');

INSERT INTO products (name, description, price, stock_quantity)
VALUES
    ('Laptop', 'High-end laptop', 1500.00, 100),
    ('Mouse', 'Wireless mouse', 25.50, 200);