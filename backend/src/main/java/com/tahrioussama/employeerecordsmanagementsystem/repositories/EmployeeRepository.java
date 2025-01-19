package com.tahrioussama.employeerecordsmanagementsystem.repositories;

import com.tahrioussama.employeerecordsmanagementsystem.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Employee entity.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Find employees by department.
     *
     * @param department the department to search for.
     * @return a list of employees in the given department.
     */
    List<Employee> findByDepartment(String department);

    /**
     * Search for employees by their job title.
     *
     * @param jobTitle the job title to search for.
     * @return a list of employees with the given job title.
     */
    List<Employee> findByJobTitle(String jobTitle);

    /**
     * Find employees by their employment status.
     *
     * @param employmentStatus the employment status (e.g., ACTIVE, INACTIVE).
     * @return a list of employees with the specified employment status.
     */
    List<Employee> findByEmploymentStatus(String employmentStatus);

    /**
     * Search for employees by a partial match on their full name.
     *
     * @param fullName the partial or full name to search for.
     * @return a list of employees whose full names match the input.
     */
    List<Employee> findByFullNameContainingIgnoreCase(String fullName);
}
