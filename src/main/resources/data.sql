DROP TABLE IF EXISTS URL_PARAMS;

CREATE TABLE URL_PARAMS (
id INT AUTO_INCREMENT PRIMARY KEY,
min_value INT,
max_value INT,
base_value INT
);

INSERT INTO URL_PARAMS (min_value, max_value, base_value) VALUES
(1,10,4),
(2,20,5),
(4,14,7);
