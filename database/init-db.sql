CREATE TABLE IF NOT EXISTS products(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	category VARCHAR(255),
	description TEXT,
	price DOUBLE PRECISION,
	stock INTEGER
);

CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY NOT NULL,
    role VARCHAR(50) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    password TEXT NOT NULL
);