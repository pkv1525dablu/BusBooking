package com.booking2.repository;

import com.booking2.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
   // Optional<Route> findByOriginAndDestination(String origin, String destination);
}