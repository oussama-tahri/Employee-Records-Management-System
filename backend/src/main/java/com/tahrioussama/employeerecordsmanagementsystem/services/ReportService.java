package com.tahrioussama.employeerecordsmanagementsystem.services;

import com.tahrioussama.employeerecordsmanagementsystem.entities.Employee;
import com.tahrioussama.employeerecordsmanagementsystem.exceptions.ResourceNotFoundException;
import com.tahrioussama.employeerecordsmanagementsystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Service layer for generating reports.
 */
@Service
public class ReportService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public ReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Generate a report of all employees.
     *
     * @return a CSV report as a ByteArrayInputStream.
     */
    public ByteArrayInputStream generateEmployeeReport() throws IOException {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found for the report.");
        }

            return CSVExporter.exportEmployeesToCSV(employees);
    }

    /**
     * Generate a report of employees filtered by department.
     *
     * @param department the department name.
     * @return a CSV report as a ByteArrayInputStream.
     */
    public ByteArrayInputStream generateReportByDepartment(String department) throws IOException {
        List<Employee> employees = employeeRepository.findByDepartment(department);

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found in the department: " + department);
        }

        return CSVExporter.exportEmployeesToCSV(employees);
    }

    /**
     * Generate a report of employees filtered by employment status.
     *
     * @param status the employment status (e.g., Active, Inactive).
     * @return a CSV report as a ByteArrayInputStream.
     */
    public ByteArrayInputStream generateReportByStatus(String status) throws IOException {
        List<Employee> employees = employeeRepository.findByEmploymentStatus(status);

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found with status: " + status);
        }

        return CSVExporter.exportEmployeesToCSV(employees);
    }
}

