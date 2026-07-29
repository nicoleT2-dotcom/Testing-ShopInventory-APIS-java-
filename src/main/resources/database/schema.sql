
CREATE DATABASE IF NOT EXISTS shop_db;

USE shop_db;


CREATE TABLE apple (
id INT AUTO_INCREMENT PRIMARY KEY,
variety VARCHAR(100) NOT NULL,
origin VARCHAR(100) NOT NULL,
colour VARCHAR(50) NOT NULL,
weight_grams INT NOT NULL,
in_season BOOLEAN NOT NULL
);


INSERT INTO apple (variety, origin, colour, weight_grams, in_season) VALUES
('Braeburn', 'Kent', 'red', 150, true),
('Granny Smith', 'New Zealand', 'green', 130, true),
('Gala', 'Herefordshire', 'red', 165, false),
('Pink Lady', 'Spain', 'pink-red', 140, true);



SELECT * FROM apple;

-- database