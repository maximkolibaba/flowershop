CREATE TABLE IF NOT EXISTS TEST.SA."Order" (
  id           IDENTITY,
  userId       BIGINT,
  createDate   TIMESTAMP,
  completeDate TIMESTAMP,
  total        DECIMAL,
  orderStatus  ENUM ('PENDING_PAYMENT', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'COMPLETED')
);