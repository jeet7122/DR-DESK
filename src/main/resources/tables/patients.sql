CREATE TABLE patients(
    id SERIAL PRIMARY KEY ,
    first_name VARCHAR(50) NOT NULL ,
    last_name VARCHAR(50),
    age INT NOT NULL,
    gender VARCHAR(20) NOT NULL,
    address VARCHAR(255),
    blood_group VARCHAR(5),
    has_chronic_disease BOOLEAN,
    registration_date TIMESTAMP DEFAULT current_timestamp
);

ALTER TABLE patients
ADD COLUMN email VARCHAR(100) UNIQUE