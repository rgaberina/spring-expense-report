INSERT INTO receipt (username, file, category, payment) values ('user1', 'Receipts/Electric Residential-1.jpg', 'Grocery', 'Cash');
INSERT INTO receipt (username, file, category, payment) values ('user1', 'Receipts/Electric Residential-1.jpg', 'Grocery', 'Cash');
INSERT INTO receipt (username, file, category, payment) values ('user1', 'Receipts/Electric Residential-1.jpg', 'Auto', 'Credit');
INSERT INTO receipt (username, file, category, payment) values ('user1', 'Receipts/Electric Residential-1.jpg', 'Clothing', 'Debit');
INSERT INTO receipt (username, file, category, payment) values ('user1', 'Receipts/Electric Residential-1.jpg', 'Clothing', 'Cash');
INSERT INTO receipt (username, file, category, payment) values ('user1', 'Receipts/Electric Residential-1.jpg', 'Grocery', 'Debit');
INSERT INTO receipt (username, file, category, payment) values ('user1', 'Receipts/Electric Residential-1.jpg', 'Shopping', 'Cash');
INSERT INTO receipt (username, file, category, payment) values ('user1', 'Receipts/Electric Residential-1.jpg', 'Clothing', 'Credit');

INSERT INTO user_roles (username, roleID) values ('user1', 2);
INSERT INTO user_roles (username, roleID) values ('user2', 2);
INSERT INTO user_roles (username, roleID) values ('user3', 2);

INSERT INTO roles values (1, 'ADMIN');
INSERT INTO roles values (2, 'USER');