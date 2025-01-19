package com.employee.service;

import com.employee.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class EmployeeService {
    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;

    public EmployeeService(ApiClient apiClient) {
        this.apiClient = new ApiClient("http://localhost:8080/api");
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Fetch all employees from the backend.
     *
     * @return List of employees.
     */
    public List<Employee> getAllEmployees() {
        try {
            String response = apiClient.get("/employees");
            return objectMapper.readValue(response, new TypeReference<List<Employee>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch employees: " + e.getMessage(), e);
        }
    }

    /**
     * Add a new employee.
     *
     * @param employee The employee object to be added.
     */
    public void createEmployee(Employee employee) {
        try {
            String employeeJson = objectMapper.writeValueAsString(employee);
            apiClient.post("/employees", employeeJson);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add employee: " + e.getMessage(), e);
        }
    }

    /**
     * Delete an employee by ID.
     *
     * @param employeeId The ID of the employee to be deleted.
     */
    public void deleteEmployee(String employeeId) {
        try {
            apiClient.delete("/employees/" + employeeId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete employee: " + e.getMessage(), e);
        }
    }

    /**
     * Search for employees based on query parameters.
     *
     * @param queryParams A map of query parameters for filtering employees.
     * @return List of filtered employees.
     */
    public List<Employee> searchEmployees(Map<String, String> queryParams) {
        try {
            StringBuilder queryString = new StringBuilder("?");

            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                queryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }

            String response = apiClient.get("/employees" + queryString.toString());
            return objectMapper.readValue(response, new TypeReference<List<Employee>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to search employees: " + e.getMessage(), e);
        }
    }

    public Employee getEmployeeById(String id) {
        return apiClient.get("/employees/" + id, Employee.class);
    }
}