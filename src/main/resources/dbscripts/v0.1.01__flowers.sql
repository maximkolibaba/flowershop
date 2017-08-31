DROP TABLE IF EXISTS TEST.SA.FLOWER;

CREATE TABLE TEST.SA.FLOWER (
  id     IDENTITY,
  name   VARCHAR(60),
  price  DECIMAL,
  amount INT
);

INSERT INTO TEST.SA.FLOWER (name, price, amount)
VALUES
  ('Rose', 80, 20),
  ('Peony', 50, 40),
  ('Violet', 40, 70);