CREATE TABLE user_details(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    gender VARCHAR(50),
    email VARCHAR(150),
    country VARCHAR(100),
    city VARCHAR(100),
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
    start_date_of_visit DATE NOT NULL,
    end_date_of_visit DATE NOT NULL,
    description VARCHAR(300),
    patient_id BIGINT REFERENCES user_details(id),
    doctor_id BIGINT REFERENCES user_details(id),
    UNIQUE(patient_id)
);

CREATE TABLE receipts(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    description TEXT NOT NULL,
    prescription_date DATE NOT NULL,
    valid_to DATE NOT NULL,
    patient_id BIGINT REFERENCES user_details(id),
    doctor_id BIGINT REFERENCES user_details(id)
);