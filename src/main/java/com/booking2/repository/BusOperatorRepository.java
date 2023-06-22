package com.booking2.repository;

import com.booking2.entities.BusOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BusOperatorRepository extends JpaRepository<BusOperator, Long> {
   // Optional<BusOperator> findByOperatorName(String operatorName);
}