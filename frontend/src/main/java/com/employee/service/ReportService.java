package com.employee.service;

public class ReportService {
    private final ApiClient apiClient;

    public ReportService() {
        this.apiClient = new ApiClient("http://localhost:8080/api");
    }

    public String generateReport() {
        return apiClient.get("/reports/export");
    }
}
