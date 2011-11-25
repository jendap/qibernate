INSERT INTO nest (id, name, address, version) VALUES (1, 'nest0.name', 'nest0.address', 1);
INSERT INTO cat (id, name, nest_id, age, hunger, version) VALUES (1, 'cat0.name', 1, 0, 10, 1);
INSERT INTO cat (id, name, nest_id, age, hunger, version) VALUES (2, 'cat1.name', 1, 1, 10, 1);
INSERT INTO kitten (id, cat_id, name, price, version) VALUES (1, 1, 'kitten0.name', 0, 1);
INSERT INTO kitten (id, cat_id, name, price, version) VALUES (2, 1, 'kitten1.name', 1, 1);
