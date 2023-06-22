package com.booking2.repository;

import com.booking2.entities.UserPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserPaymentMethodRepository extends JpaRepository<UserPaymentMethod, Long> {
    //List<UserPaymentMethod> findByUserId(Long userId);
}