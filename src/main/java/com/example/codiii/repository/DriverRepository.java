package com.example.codiii.repository;

import com.example.codiii.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByFirstName(String name);
    Optional<Driver> findByLastName(String name);
    Optional<Driver> findById(Long id);
}
