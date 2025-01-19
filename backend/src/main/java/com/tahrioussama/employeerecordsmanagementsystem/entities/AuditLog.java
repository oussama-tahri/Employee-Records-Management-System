package com.tahrioussama.employeerecordsmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity representing an audit log for tracking changes in the system.
 */
@Getter
@Entity
@Table(name = "audit_logs")
public class AuditLog {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "performed_by", nullable = false)
    private String performedBy;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "details", columnDefinition = "TEXT")
    private String details;

    // Default constructor
    public AuditLog() {
    }

    // Parameterized constructor
    public AuditLog(String action, String performedBy, LocalDateTime timestamp, String details) {
        this.action = action;
        this.performedBy = performedBy;
        this.timestamp = timestamp;
        this.details = details;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    // equals, hashCode, and toString for better usability
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditLog auditLog = (AuditLog) o;
        return Objects.equals(id, auditLog.id) &&
                Objects.equals(action, auditLog.action) &&
                Objects.equals(performedBy, auditLog.performedBy) &&
                Objects.equals(timestamp, auditLog.timestamp) &&
                Objects.equals(details, auditLog.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, action, performedBy, timestamp, details);
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", performedBy='" + performedBy + '\'' +
                ", timestamp=" + timestamp +
                ", details='" + details + '\'' +
                '}';
    }
}
