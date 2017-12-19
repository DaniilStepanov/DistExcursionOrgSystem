package com.kspt.eos.repository;

import com.kspt.eos.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findById(Long id);
    Optional<Vehicle> findByDriverUserName(String name);
    Optional<Vehicle> findByDriverUserLogin(String login);

}
