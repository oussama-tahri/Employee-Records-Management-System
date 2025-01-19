package com.tahrioussama.employeerecordsmanagementsystem.services;


import com.tahrioussama.employeerecordsmanagementsystem.entities.Employee;
import com.tahrioussama.employeerecordsmanagementsystem.exceptions.ResourceNotFoundException;
import com.tahrioussama.employeerecordsmanagementsystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for managing employee operations.
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Retrieve all employees.
     *
     * @return a list of EmployeeDTO objects.
     */
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeRepository.findAll());
    }

    /**
     * Find an employee by ID.
     *
     * @param id the employee's ID.
     * @return the corresponding EmployeeDTO.
     * @throws ResourceNotFoundException if no employee is found.
     */
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
    }

    /**
     * Create a new employee.
     *
     * @param employee the employee data transfer object.
     * @return the created EmployeeDTO.
     */
    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Update an existing employee.
     *
     * @param id          the employee's ID.
     * @param employee the updated employee data.
     * @return the updated EmployeeDTO.
     * @throws ResourceNotFoundException if no employee is found.
     */
    @Transactional
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

        existingEmployee.setFullName(employee.getFullName());
        existingEmployee.setJobTitle(employee.getJobTitle());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setEmploymentStatus(employee.getEmploymentStatus());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setContactInformation(employee.getContactInformation());

        return existingEmployee;
    }

    /**
     * Delete an employee by ID.
     *
     * @param id the employee's ID.
     * @throws ResourceNotFoundException if no employee is found.
     */
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
        employeeRepository.delete(employee);
    }

    /**
     * Search employees by name.
     *
     * @param name the name or partial name to search.
     * @return a list of EmployeeDTOs matching the search criteria.
     */
    @Transactional(readOnly = true)
    public List<Employee> searchEmployeesByName(String name) {
        return new ArrayList<>(employeeRepository.findByFullNameContainingIgnoreCase(name));
    }
}

