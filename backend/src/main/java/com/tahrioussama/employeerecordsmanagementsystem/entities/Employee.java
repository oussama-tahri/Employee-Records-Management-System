package com.tahrioussama.employeerecordsmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing an Employee.
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "employees", uniqueConstraints = {
        @UniqueConstraint(columnNames = "employee_id")
})
public class Employee {

    // Getters and Setters
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "full_name", nullable = false)
    @NotBlank(message = "Full name is required")
    @JsonProperty("fullName")
    private String fullName;

    @Column(name = "job_title", nullable = false)
    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @Column(name = "department", nullable = false)
    @NotBlank(message = "Department is required")
    private String department;

    @Column(name = "employment_status", nullable = false)
    @NotBlank(message = "Employment status is required")
    private String employmentStatus;

    @Column(name = "contact_info", nullable = false)
    @NotBlank(message = "Contact information is required")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Contact information must be a valid email address")
    private String contactInformation;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Address is required")
    private String address;
    @ManyToMany
    @JoinTable(
            name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    // Parameterized constructor
    public Employee(String fullName, String jobTitle, String department,
                    String employmentStatus, String contactInformation, String address) {
        this.fullName = fullName;
        this.jobTitle = jobTitle;
        this.department = department;
        this.employmentStatus = employmentStatus;
        this.contactInformation = contactInformation;
        this.address = address;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public String getAddress() {
        return address;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    // equals, hashCode, and toString for better usability
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(fullName, employee.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", department='" + department + '\'' +
                ", employmentStatus='" + employmentStatus + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
