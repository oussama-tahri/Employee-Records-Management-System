package com.tahrioussama.employeerecordsmanagementsystem.services;

import com.tahrioussama.employeerecordsmanagementsystem.entities.Employee;

import java.io.*;
import java.util.List;

/**
 * Utility class for exporting data to CSV files.
 */
public class CSVExporter {

    private static final String[] HEADERS = {
            "Employee ID", "Full Name", "Job Title", "Department",
            "Hire Date", "Employment Status", "Contact Information", "Address"
    };

    private CSVExporter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Exports a list of EmployeeDTO objects to a CSV file.
     *
     * @param employees the list of employees to export
     * @throws IOException if an I/O error occurs
     */
    public static ByteArrayInputStream exportEmployeesToCSV(List<Employee> employees) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             Writer writer = new OutputStreamWriter(byteArrayOutputStream)) {

            // Write headers
            writer.write(String.join(",", HEADERS) + "\n");

            // Write employee data
            for (Employee employee : employees) {
                writer.write(formatEmployee(employee) + "\n");
            }

            writer.flush();
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }
    }


    /**
     * Formats an EmployeeDTO object as a CSV row.
     *
     * @param employee the employee to format
     * @return a CSV-formatted string representing the employee
     */
    private static String formatEmployee(Employee employee) {
        return String.join(",",
                employee.getFullName(),
                employee.getJobTitle(),
                employee.getDepartment(),
                employee.getEmploymentStatus(),
                employee.getContactInformation(),
                employee.getAddress()
        );
    }
}