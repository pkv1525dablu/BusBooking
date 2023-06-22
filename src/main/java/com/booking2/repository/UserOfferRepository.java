package com.booking2.repository;


import com.booking2.entities.UserOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserOfferRepository extends JpaRepository<UserOffer, Long> {
}