CREATE TABLE IF NOT EXISTS "ORDER" (
  id           IDENTITY,
  userId       BIGINT,
  createDate   TIMESTAMP,
  completeDate TIMESTAMP,
  total        DECIMAL,
  orderStatus  ENUM ('PENDING_PAYMENT', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'COMPLETED'),
  FOREIGN KEY (userId) REFERENCES USER (id)
);