-- Insert roles
INSERT INTO roles (name) VALUES ('HR');
INSERT INTO roles (name) VALUES ('Manager');
INSERT INTO roles (name) VALUES ('Administrator');

-- Insert employees
INSERT INTO employees (full_name, job_title, department, hire_date, employment_status, contact_information, address)
VALUES ('Oussama Tahri', 'Software Engineer', 'IT', 'Active', 'oussot@example.com', '123 Main Street, Casablanca, Morocco');

INSERT INTO employees (full_name, employee_id, job_title, department, hire_date, employment_status, contact_information, address)
VALUES ('Marouane Bouchtaoui', 'HR Manager', 'HR', 'Active', 'mrnbcht@example.com', '456 Elm Street, Casablanca, Morocco');