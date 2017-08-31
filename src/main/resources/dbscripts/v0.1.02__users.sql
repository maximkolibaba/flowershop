CREATE TABLE IF NOT EXISTS USER (
  id        IDENTITY,
  firstName VARCHAR(50),
  lastName  VARCHAR(50),
  login     VARCHAR(50),
  password  VARCHAR(50),
  userType  ENUM ('CUSTOMER', 'ADMIN'),
  address   VARCHAR(50),
  balance   DECIMAL,
  discount  INT
);