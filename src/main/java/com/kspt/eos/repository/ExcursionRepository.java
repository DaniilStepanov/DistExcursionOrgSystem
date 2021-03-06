package com.kspt.eos.repository;

import com.kspt.eos.entity.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
    Optional<Excursion> findById(Long id);
    Optional<Excursion> findByName(String name);
    List<Excursion> findAll();
}
