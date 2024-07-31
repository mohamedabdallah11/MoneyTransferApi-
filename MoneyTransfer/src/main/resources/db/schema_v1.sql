CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) UNIQUE,
                      first_name VARCHAR(255),
                      last_name VARCHAR(255),
                      email VARCHAR(255),
                      password VARCHAR(255),
                      phone_number INT,
                      date_of_birth DATE,
                      nationality VARCHAR(255),
                      national_id VARCHAR(255),
                      address VARCHAR(255),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      is_active INT
);

CREATE TABLE authorities (
                             username VARCHAR(255),
                             authority VARCHAR(255),
                             PRIMARY KEY (username),
                             CONSTRAINT fk_authorities_username FOREIGN KEY (username) REFERENCES user (username)
);

CREATE TABLE account (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         account_no VARCHAR(255) UNIQUE,
                         name VARCHAR(255),
                         pin VARCHAR(255),
                         balance DOUBLE PRECISION,
                         account_type VARCHAR(255),
                         user_id INT,
                         CONSTRAINT fk_account_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE transaction (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             sender_id INT,
                             receiver_id INT,
                             amount DOUBLE PRECISION,
                             date DATE,
                             status VARCHAR(255),
                             CONSTRAINT fk_transaction_sender_id FOREIGN KEY (sender_id) REFERENCES account (id),
                             CONSTRAINT fk_transaction_receiver_id FOREIGN KEY (receiver_id) REFERENCES account (id)
);

CREATE TABLE favourite_recipients (
                                      user1_id INT,
                                      user2_id INT,
                                      PRIMARY KEY (user1_id, user2_id),
                                      CONSTRAINT fk_favourite_recipients_user1_id FOREIGN KEY (user1_id) REFERENCES user (id),
                                      CONSTRAINT fk_favourite_recipients_user2_id FOREIGN KEY (user2_id) REFERENCES user (id)
);