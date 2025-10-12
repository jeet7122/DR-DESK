CREATE TABLE billing(
    bill_id SERIAL PRIMARY KEY,
    appointment_id INT,
    patient_id INT,
    consultation_fee DECIMAL(10,2),
    medicine_fee DECIMAL(10,2),
    service_fee DECIMAL(10,2),
    total_amount DECIMAL(10,2),
    billing_date TIMESTAMP DEFAULT current_timestamp,
    paid BOOLEAN,
    foreign key (appointment_id) REFERENCES appointment(appointment_id),
    foreign key (patient_id) REFERENCES patients(id)
);