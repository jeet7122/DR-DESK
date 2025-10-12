CREATE TABLE doctors(
    doctor_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50),
    specialization VARCHAR(50),
    contact_number VARCHAR(15),
    address VARCHAR(255),
    department_id INT,
    joining_date DATE DEFAULT current_timestamp,
    is_available BOOLEAN
);