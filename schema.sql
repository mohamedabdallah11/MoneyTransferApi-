CREATE TABLE `user` (
  `email` varchar(255) PRIMARY KEY,
  `username` varchar(255),
  `password` varchar(70),
  `gender` varchar(50),
  `date_of_birth` date,
  `country` varchar(50),
  `is_active` integer
);

CREATE TABLE `authorities` (
  `user_email` varchar(255) PRIMARY KEY,
  `authority` varchar(255),
  FOREIGN KEY (`user_email`) REFERENCES `user` (`email`)
);

CREATE TABLE `card` (
  `card_number` varchar(255) PRIMARY KEY,
  `cardholder_name` varchar(255),
  `expiration_date` CHAR(7), -- Format YYYY-MM
  `cvv` varchar(5),
  `pin` varchar(10),
  `balance` double,
  `currency` varchar(5),
  `account_type` varchar(50),
  `user_email` varchar(255),
  FOREIGN KEY (`user_email`) REFERENCES `user` (`email`)
);

CREATE TABLE `transaction` (
  `transaction_id` INT AUTO_INCREMENT PRIMARY KEY,
  `sender_card_number` varchar(255),
  `sender_username` varchar(255),
  `recipient_card_number` varchar(255),
  `recipient_username` varchar(255),
  `sender_email` varchar(255),
  `recipient_email` varchar(255),
  `amount` double,
  `date` date,
  `status` varchar(50),
  FOREIGN KEY (`sender_card_number`) REFERENCES `card` (`card_number`),
  FOREIGN KEY (`recipient_card_number`) REFERENCES `card` (`card_number`)
);

CREATE TABLE `favourite_recipients` (
  `user_email1` varchar(255),
  `user_email2` varchar(255),
  PRIMARY KEY (`user_email1`, `user_email2`),
  FOREIGN KEY (`user_email1`) REFERENCES `user` (`email`),
  FOREIGN KEY (`user_email2`) REFERENCES `user` (`email`)
);
