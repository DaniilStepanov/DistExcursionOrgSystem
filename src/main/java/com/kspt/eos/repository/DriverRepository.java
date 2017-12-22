package com.kspt.eos.repository;

import com.kspt.eos.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findById(Long id);
    Optional<Driver> findByUserLogin(String login);
    Optional<Driver> findByUserId(Long id);
    List<Driver> findAll();
}
