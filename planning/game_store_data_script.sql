insert into game(title, esrb_rating, description, price, studio, quantity)
values ("War", "MA", "Bloody first person shooter", 49.99, "NY studios", 25),
("War", "MA", "Bloody first person shooter", 49.99, "NY studios", 25),
("War", "MA", "Bloody first person shooter", 49.99, "NY studios", 25),
("War", "MA", "Bloody first person shooter", 49.99, "NY studios", 25),
("War", "MA", "Bloody first person shooter", 49.99, "NY studios", 25);

insert into console(model, manufacturer, memory_amount, processor, price, quantity)
values
("E54", "Evan Co.", "8bit", "xap intel", 349.99, 30),
("PPP", "Evan Co.", "8bit", "None", 349.99, 30),
("E54", "Evan Co.", "8bit", "xap intel", 249.99, 30),
("E54", "Evan Co.", "8bit", "xap intel", 349.99, 30),
("E54", "Evan Co.", "8bit", "xap intel", 349.99, 30);

insert into t_shirt (size, color, description, price, quantity)
values
("L", "Black", "1980s vintage gamer", 14.49, 11),
("XXL", "Black", "1980s vintage gamer", 14.49, 11),
("XL", "Yellow", "1980s vintage gamer", 14.49, 11),
("M", "Black", "1980s vintage gamer", 14.49, 11),
("S", "Black", "1980s vintage gamer", 14.49, 11);


insert into invoice(
name, street, city, state, zipcode, item_type, item_id, unit_price,
quantity, subtotal, tax, processing_fee, total) values
("Evan", "main street", "Charlotte", "NC", "29745", "game", 1,
10.99, 3, 33.97, 3.97, 1.49, 40.25),
("Scott", "main street", "Charlotte", "NC", "29745", "game", 2,
10.99, 3, 33.97, 3.97, 1.49, 40.25),
("Wilson", "main street", "Charlotte", "NC", "29745", "game", 2,
10.99, 3, 33.97, 3.97, 1.49, 40.25),
("Jess", "main street", "Charlotte", "NC", "29745", "game", 3,
10.99, 3, 33.97, 3.97, 1.49, 40.25),
("Yvonne", "main street", "Charlotte", "NC", "29745", "game", 3,
10.99, 3, 33.97, 3.97, 1.49, 40.25),
("Milania", "main street", "Charlotte", "NC", "29745", "game", 3,
10.99, 3, 33.97, 3.97, 1.49, 40.25);