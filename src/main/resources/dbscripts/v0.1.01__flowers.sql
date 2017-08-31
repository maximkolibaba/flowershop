CREATE TABLE FLOWER (
  id     IDENTITY,
  name   VARCHAR(50),
  price  DECIMAL,
  amount INT
);

INSERT INTO FLOWER (name, price, amount)
VALUES
  ('Rose', 80, 20),
  ('Peony', 50, 42),
  ('Violet', 40, 73),
  ('Lily', 72, 60),
  ('Cornflower', 63, 69),
  ('Daffodil', 41, 85),
  ('Iris', 38, 60),
  ('Forget-me-not', 61, 86),
  ('Hyacinth', 25, 73),
  ('Mimosa', 81, 62),
  ('Orchid', 54, 75),
  ('Windflower', 32, 55);