CREATE TABLE IF NOT EXISTS USER (
  id        IDENTITY,
  firstName VARCHAR(50),
  lastName  VARCHAR(50),
  login     VARCHAR(50),
  password  VARCHAR(50),
  isAdmin   BOOLEAN,
  address   VARCHAR(50),
  balance   DECIMAL,
  discount  INT
);

INSERT INTO USER (firstName, lastName, login, password, isAdmin, address, balance, discount)
VALUES ('admin', 'admin', 'admin', 'admin123', TRUE, 'admin', 9999, 99);