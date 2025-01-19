package com.tahrioussama.employeerecordsmanagementsystem.repositories;

import com.tahrioussama.employeerecordsmanagementsystem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Role entity.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Find a role by its name.
     *
     * @param name the role name to search for.
     * @return an optional containing the role, if found.
     */
    Optional<Role> findByRoleName(String name);
}
