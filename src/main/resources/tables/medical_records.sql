CREATE TABLE medical_records(
    id SERIAL PRIMARY KEY,
    patient_id INT,
    condition VARCHAR(50),
    description VARCHAR(255),
    date_diagnosed DATE DEFAULT current_timestamp,
    current_status VARCHAR(50),
    foreign key (patient_id) REFERENCES patients(id)
);