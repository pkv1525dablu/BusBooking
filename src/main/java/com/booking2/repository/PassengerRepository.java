package com.booking2.repository;

import com.booking2.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}