SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS shoes(
    id int PRIMARY KEY auto_increment,
    brand VARCHAR,
    shoeColor VARCHAR,
    shoeSize DOUBLE,
    price DOUBLE,
    createdAt TIMESTAMP
);