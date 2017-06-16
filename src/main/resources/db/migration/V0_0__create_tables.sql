CREATE TABLE address (
  id INT AUTO_INCREMENT PRIMARY KEY,
  street VARCHAR(30) NOT NULL
);

CREATE TABLE user (
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  age INT(3) NOT NULL,
  address_id INT NOT NULL,
  FOREIGN KEY (address_id) REFERENCES address (id)
);

INSERT INTO address (street) VALUES
  ("Kozlaniuka"),
  ("Doroshenka"),
  ("Buchacha");