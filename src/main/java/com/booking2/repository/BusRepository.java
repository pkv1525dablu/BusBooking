package com.booking2.repository;

import com.booking2.entities.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    //List<Bus> findByOperatorId(Long operatorId);
}