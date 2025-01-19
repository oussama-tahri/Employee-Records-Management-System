package com.tahrioussama.employeerecordsmanagementsystem.repositories;

import com.tahrioussama.employeerecordsmanagementsystem.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for AuditLog entity.
 */
@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    /**
     * Find audit logs by the action performed.
     *
     * @param action the action to search for.
     * @return a list of audit logs matching the given action.
     */
    List<AuditLog> findByAction(String action);

    /**
     * Find audit logs by the user who performed the action.
     *
     * @param performedBy the username to search for.
     * @return a list of audit logs for actions performed by the specified user.
     */
    List<AuditLog> findByPerformedBy(String performedBy);

    /**
     * Find audit logs within a specific time range.
     *
     * @param start the start of the time range.
     * @param end the end of the time range.
     * @return a list of audit logs within the specified range.
     */
    List<AuditLog> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
