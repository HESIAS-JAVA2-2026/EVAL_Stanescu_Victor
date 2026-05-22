package com.hesias.repository;

import com.hesias.entity.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {

    Optional<Garage> findByName(String name);
}
