CREATE TABLE user_details(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    gender VARCHAR(50),
    email VARCHAR(150),
    country VARCHAR(100),
    city VARCHAR(100),
    street VARCHAR(150),
    postal_code VARCHAR(20),
    date_of_birth DATE
);

CREATE TABLE users(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    role VARCHAR(30) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    user_details_id BIGINT REFERENCES user_details(id),
    UNIQUE(user_details_id)
);

CREATE TABLE medical_visits(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    start_date_of_visit TIMESTAMP NOT NULL,
    end_date_of_visit TIMESTAMP NOT NULL,
    description VARCHAR(300),
    patient_id BIGINT REFERENCES user_details(id),
    doctor_id BIGINT REFERENCES user_details(id)
);

CREATE TABLE receipts(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    description TEXT NOT NULL,
    prescription_date DATE NOT NULL,
    valid_to DATE NOT NULL,
    patient_id BIGINT REFERENCES user_details(id),
    doctor_id BIGINT REFERENCES user_details(id)
);

-- Initial data (to be dropped later!!!)
INSERT INTO user_details (first_name, last_name, gender, email, country, city, street, postal_code, date_of_birth)
    VALUES ('Jan', 'Kowalski', 'MALE', 'jan.kowalski@gmail.com', 'Poland', 'Lublin', 'Hetmańska 2', '20-234', DATE '1988-01-09');
INSERT INTO user_details (first_name, last_name, gender, email, country, city, street, postal_code, date_of_birth)
    VALUES ('Stanisław', 'Wiśniewski', 'MALE', 'stanislaw.wisniweski@gmail.com', 'Poland', 'Lublin', 'Al. Kraśnicka 128 2', '20-718', DATE '1991-10-24');

INSERT INTO users (role, username, password, user_details_id)
    VALUES ('DOCTOR', 'jan123', 'password123', 1);
INSERT INTO users (role, username, password, user_details_id)
    VALUES ('PATIENT', 'stan123', 'password123', 2);

INSERT INTO medical_visits(start_date_of_visit, end_date_of_visit, description, patient_id, doctor_id)
VALUES ('2021-11-10 09:30:00-08', '2021-11-10 10:00:00-08', 'wizyta kontrolna', 2, 1);

INSERT INTO receipts(description, prescription_date, valid_to, patient_id, doctor_id)
	VALUES ('rutinoscorbin 3x dziennie', DATE '2021-11-10', DATE '2021-11-20', 2, 1);