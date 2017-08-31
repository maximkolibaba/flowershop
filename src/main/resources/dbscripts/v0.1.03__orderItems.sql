CREATE TABLE IF NOT EXISTS OrderItem (
  id       IDENTITY,
  orderId  BIGINT,
  flowerId BIGINT,
  amount   INT,
  FOREIGN KEY (orderId) REFERENCES "Order" (id),
  FOREIGN KEY (flowerId) REFERENCES Flower (id)
);