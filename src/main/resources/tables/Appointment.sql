CREATE TABLE appointment(
    appointment_id SERIAL Primary Key,
    doctor_id INT,
    patient_id INT,
    status VARCHAR(20),
    appointment_date_and_time TIMESTAMP,
    notes VARCHAR(255)
);