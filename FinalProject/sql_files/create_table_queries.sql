CREATE DATABASE IF NOT EXISTS CSCI5308_13_PRODUCTION;
USE CSCI5308_13_PRODUCTION;

# Host: db-5308.cs.dal.ca
# Port: 3306
# Database: CSCI5308_13_PRODUCTION
# Username: CSCI5308_13_PRODUCTION_USER
# Password: hHMF8k46rPR

CREATE TABLE IF NOT EXISTS security_question_user (
	security_question_id INT AUTO_INCREMENT,
    security_question VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (security_question_id)
);

CREATE TABLE IF NOT EXISTS user (
	user_id INT AUTO_INCREMENT,
	first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    gender ENUM('M','F','O') NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    contact_number VARCHAR(10) NOT NULL UNIQUE,
    blood_group ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-') NOT NULL,
    address_first_line TEXT NOT NULL,
    address_street TEXT NOT NULL,
    address_city TEXT NOT NULL,
    address_province TEXT NOT NULL,
    address_zip_code VARCHAR(6) NOT NULL,
    address_country TEXT NOT NULL,
    account_active BOOLEAN NOT NULL DEFAULT true,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS user_security_question_user(
	user_id INT NOT NULL,
    security_question_id INT NOT NULL,
    security_question_ans TEXT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (security_question_id) REFERENCES security_question_user(security_question_id)
);

CREATE TABLE IF NOT EXISTS security_question_blood_bank (
	security_question_id INT AUTO_INCREMENT,
    security_question VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (security_question_id)
);

CREATE TABLE IF NOT EXISTS blood_bank (
	blood_bank_id INT AUTO_INCREMENT,
	name TEXT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    contact_number VARCHAR(10) NOT NULL UNIQUE,
    address_first_line TEXT NOT NULL,
    address_street TEXT NOT NULL,
    address_city TEXT NOT NULL,
    address_province TEXT NOT NULL,
    address_zip_code VARCHAR(6) NOT NULL,
    address_country TEXT NOT NULL,
    account_active BOOLEAN NOT NULL DEFAULT true,
    PRIMARY KEY (blood_bank_id)
);

CREATE TABLE IF NOT EXISTS blood_bank_security_question_blood_bank(
	blood_bank_id INT NOT NULL,
    security_question_id INT NOT NULL,
    security_question_ans TEXT NOT NULL,
	FOREIGN KEY (blood_bank_id) REFERENCES blood_bank(blood_bank_id),
    FOREIGN KEY (security_question_id) REFERENCES security_question_blood_bank(security_question_id)
);

CREATE TABLE IF NOT EXISTS blood_stock (
	blood_stock_id INT AUTO_INCREMENT,
	blood_bank_id INT NOT NULL,
    blood_group ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-') NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    threshold INT NOT NULL DEFAULT 0,
    unit_price DOUBLE NOT NULL DEFAULT 0,
    PRIMARY KEY (blood_stock_id),
    FOREIGN KEY (blood_bank_id) REFERENCES blood_bank(blood_bank_id)
);

CREATE TABLE IF NOT EXISTS blood_bank_rating(
	rating_id INT AUTO_INCREMENT,
    blood_bank_id INT NOT NULL,
    user_id INT NOT NULL,
    comment TEXT NOT NULL,
    star FLOAT NOT NULL,
    age_during_rating INT NOT NULL,
    created_at DATE NOT NULL,
    PRIMARY KEY (rating_id)
);

CREATE TABLE vaccine(
	vaccine_id int AUTO_INCREMENT,
	user_id int,
	vaccine_type varchar(255),
	registration_date varchar(255),
	first_dose_date varchar(255),
	second_dose_date varchar(255),
	PRIMARY KEY (vaccine_id),
	FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS blood_donation_request (
	request_id INT AUTO_INCREMENT,
	user_id INT NOT NULL,
	request_date VARCHAR(45) NOT NULL,
	status_change_date VARCHAR(45) NOT NULL,
	status ENUM('active', 'request', 'rejected', 'fulfilled'),
	certificate_id VARCHAR(255),
	PRIMARY KEY (request_id),
	FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS blood_receiver_request (
	request_id INT AUTO_INCREMENT,
	user_id INT NOT NULL,
	blood_group ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-') NOT NULL,
	quantity INT NOT NULL,
	request_date DATE NOT NULL,
	status ENUM('active', 'request', 'rejected','fulfilled'),
	status_change_date DATE NOT NULL,
	PRIMARY KEY (request_id),
	FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS camp (
	camp_id INT AUTO_INCREMENT,
	organizer_name VARCHAR(45),
	blood_bank_id INT NOT NULL,
	camp_date VARCHAR(45),
	camp_time VARCHAR(45),
	available_capacity INT,
	venue varchar(45),
	city varchar(45) ,
	contact varchar(15),
	PRIMARY KEY (camp_id),
	FOREIGN KEY (blood_bank_id) REFERENCES blood_bank(blood_bank_id)
);

CREATE TABLE IF NOT EXISTS admin(
	admin_id INT AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    PRIMARY KEY (admin_id)
);

INSERT INTO security_question_user(security_question) VALUES ("What is the name of your favorite childhood friend?");
INSERT INTO security_question_user(security_question) VALUES ("What is your mother’s maiden name?");
INSERT INTO security_question_user(security_question) VALUES ("What is the country of your ultimate dream vacation?");
INSERT INTO security_question_user(security_question) VALUES ("Who was your childhood hero?");
INSERT INTO security_question_user(security_question) VALUES ("What is your preferred musical genre?");

INSERT INTO security_question_blood_bank(security_question) VALUES ("How many people started the organization?");
INSERT INTO security_question_blood_bank(security_question) VALUES ("When was organization started");

#Password: Dummy1@12345
#Password: Dummy2@12345
#Password: Dummy3@12345
#Password: Dummy4@12345
#Password: Dummy5@12345
CALL blood_bank_registration("Dummy BB 1", "dummy1@gmail.com", "b7382fcfa4aa0685dbc175d704c7e949e98ef5d009b798e8d8cbed63053eb603", "9021111111", "LakeView Buildings", "2302 Brook St", "Halifax", "Nova Scotia", "B4V2V8", "Canada", 1, "8", 2, "1993-01-01");
CALL blood_bank_registration("Dummy BB 2", "dummy2@gmail.com", "7bb98024991e20e213aad1af6ca936b72298af4e2c038bde98bd46c20681a1a4", "9022222222", "LakeView Buildings", "2302 Brook St", "Newcombville", "Ontario", "B4V2V8", "Canada", 1, "8", 2, "1993-01-01");
CALL blood_bank_registration("Dummy BB 3", "dummy3@gmail.com", "3fb769899c80b788d2110af5382751ff120cb3026c57aa4f073502489fd563fe", "9023333333", "LakeView Buildings", "2302 Brook St", "Halifax", "Nova Scotia", "B4V2V8", "Canada", 1, "8", 2, "1993-01-01");
CALL blood_bank_registration("Dummy BB 4", "dummy4@gmail.com", "2d39e1a490199f0bfef04966f9b110d67b2f40810e0bfc1cf3776965c11fce57", "9024444444", "LakeView Buildings", "2302 Brook St", "Newcombville", "Ontario", "B4V2V8", "Canada", 1, "8", 2, "1993-01-01");
CALL blood_bank_registration("Dummy BB 5", "dummy5@gmail.com", "246c96a906d03fa984c1696fa5f82127170d97bc018dd9412106f3b1308859f7", "9025555555", "LakeView Buildings", "2302 Brook St", "Halifax", "Nova Scotia", "B4V2V8", "Canada", 1, "8", 2, "1993-01-01");

INSERT INTO blood_bank_rating(blood_bank_id, user_id, comment, star, age_during_rating, created_at) VALUES
(1, 7, "dummy comment", 4, 19, "2021-07-25"),
(1, 8, "dummy comment", 4, 22, "2021-07-25"),
(1, 9, "dummy comment", 4.5, 32, "2021-07-25"),
(1, 10, "dummy comment", 2, 36, "2021-07-25"),
(1, 11, "dummy comment", 2, 45, "2021-07-25"),
(1, 12, "dummy comment", 5, 65, "2021-07-25"),
(2, 7, "dummy comment", 2, 19, "2021-07-25"),
(2, 8, "dummy comment", 2, 22, "2021-07-25"),
(2, 9, "dummy comment", 3, 32, "2021-07-25"),
(2, 10, "dummy comment", 4.5, 36, "2021-07-25"),
(2, 11, "dummy comment", 1.5, 45, "2021-07-25"),
(2, 12, "dummy comment", 2.5, 65, "2021-07-25"),
(3, 7, "dummy comment", 3.5, 19, "2021-07-25"),
(3, 8, "dummy comment", 2.5, 22, "2021-07-25"),
(3, 9, "dummy comment", 3.5, 32, "2021-07-25"),
(3, 10, "dummy comment", 4.5, 36, "2021-07-25"),
(3, 11, "dummy comment", 2.5, 45, "2021-07-25"),
(3, 12, "dummy comment", 2.5, 65, "2021-07-25"),
(4, 7, "dummy comment", 2.5, 19, "2021-07-25"),
(4, 8, "dummy comment", 1.5, 22, "2021-07-25"),
(4, 9, "dummy comment", 3, 32, "2021-07-25"),
(4, 10, "dummy comment", 4, 36, "2021-07-25"),
(4, 11, "dummy comment", 1, 45, "2021-07-25"),
(4, 12, "dummy comment", 2, 65, "2021-07-25"),
(5, 7, "dummy comment", 4.5, 19, "2021-07-25"),
(5, 8, "dummy comment", 1, 22, "2021-07-25"),
(5, 9, "dummy comment", 2, 32, "2021-07-25"),
(5, 10, "dummy comment", 4.5, 36, "2021-07-25"),
(5, 11, "dummy comment", 2, 45, "2021-07-25"),
(5, 12, "dummy comment", 3, 65, "2021-07-25");

#Password: Dhruvi@123
INSERT INTO admin (email, password) VALUES ("dhruvi.shah@gmail.com", "9528eaa32f2fcc5d3c73dd202e929fcd686f4f3cc898bdc155d8502d66504c26");