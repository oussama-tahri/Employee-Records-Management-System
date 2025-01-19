package com.employee.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Employee {
    private String fullName;
    private String jobTitle;
    private String department;
    private String employmentStatus;
    private String contactInformation;
    private String address;

    public Employee() {
    }

    @JsonCreator
    public Employee(
            @JsonProperty("fullName") String fullName,
            @JsonProperty("jobTitle") String jobTitle,
            @JsonProperty("department") String department,
            @JsonProperty("employmentStatus") String employmentStatus,
            @JsonProperty("contactInformation") String contactInformation,
            @JsonProperty("address") String address
    ) {
        this.fullName = fullName;
        this.jobTitle = jobTitle;
        this.department = department;
        this.employmentStatus = employmentStatus;
        this.contactInformation = contactInformation;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}