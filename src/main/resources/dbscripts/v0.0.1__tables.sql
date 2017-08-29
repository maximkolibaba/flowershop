CREATE TABLE IF NOT EXISTS User (
  id        IDENTITY,
  firstName TEXT,
  lastName  TEXT,
  login     TEXT,
  password  TEXT,
  userType  ENUM ('CUSTOMER', 'ADMIN'),
  address   TEXT,
  balance   DECIMAL,
  discount  INT
);

CREATE TABLE IF NOT EXISTS Flower (
  id     IDENTITY,
  name   TEXT,
  price  DECIMAL,
  amount INT
);

CREATE TABLE IF NOT EXISTS "Order" (
  id IDENTITY
);

CREATE TABLE IF NOT EXISTS OrderItem (
  id       IDENTITY,
  orderId  INT,
  flowerId INT,
  amount   INT,
  FOREIGN KEY (orderId) REFERENCES "Order" (id),
  FOREIGN KEY (flowerId) REFERENCES Flower (id)
);