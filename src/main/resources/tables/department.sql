CREATE TABLE department(
    department_id SERIAL PRIMARY KEY,
    department_name VARCHAR(50),
    location VARCHAR(50),
    description VARCHAR(50)
);
ALTER TABLE department
ALTER description TYPE VARCHAR(255);