package com.tahrioussama.employeerecordsmanagementsystem.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Data Transfer Object for Employee entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotNull(message = "Employee ID cannot be null")
    private Long id;

    @NotBlank(message = "Full name is mandatory")
    private String fullName;

    @NotBlank(message = "Job title is mandatory")
    private String jobTitle;

    @NotBlank(message = "Department is mandatory")
    private String department;

    @Past(message = "Hire date must be in the past")
    private LocalDate hireDate;

    @NotBlank(message = "Employment status is mandatory")
    private String employmentStatus;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Contact information is mandatory")
    private String contactInformation;

    @NotBlank(message = "Address is mandatory")
    private String address;
}

