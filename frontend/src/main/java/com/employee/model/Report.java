package com.employee.model;

public class Report {
    private String id;
    private String name;
    private String generatedDate;

    public Report() {
    }

    public Report(String id, String name, String generatedDate) {
        this.id = id;
        this.name = name;
        this.generatedDate = generatedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(String generatedDate) {
        this.generatedDate = generatedDate;
    }
}

