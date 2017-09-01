CREATE TABLE IF NOT EXISTS ORDERITEM (
  id       IDENTITY,
  orderId  BIGINT,
  flowerId BIGINT,
  amount   INT,
  FOREIGN KEY (orderId) REFERENCES "ORDER" (id),
  FOREIGN KEY (flowerId) REFERENCES FLOWER (id)
);