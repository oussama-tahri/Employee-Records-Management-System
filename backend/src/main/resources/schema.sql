-- Create Employee table
CREATE TABLE employees (
                           id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                           full_name VARCHAR2(100) NOT NULL,
                           job_title VARCHAR2(100) NOT NULL,
                           department VARCHAR2(100) NOT NULL,
                           employment_status VARCHAR2(50) NOT NULL,
                           contact_information VARCHAR2(100),
                           address VARCHAR2(255)
);

-- Create Role table
CREATE TABLE roles (
                       id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                       name VARCHAR2(50) UNIQUE NOT NULL
);

-- Create AuditLog table
CREATE TABLE audit_logs (
                            id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                            action VARCHAR2(50) NOT NULL,
                            entity_name VARCHAR2(100) NOT NULL,
                            timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            performed_by VARCHAR2(100) NOT NULL
);

-- Create foreign key relationships
ALTER TABLE employees
    ADD CONSTRAINT fk_employee_role FOREIGN KEY (department) REFERENCES roles(name);