package com.booking2.repository;

import com.booking2.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    // List<UserRole> findByUserId(Long userId);
}

