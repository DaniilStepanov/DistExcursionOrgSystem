package com.kspt.eos.repository;

import com.kspt.eos.entity.ExcursionObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExcursionObjectRepository extends JpaRepository<ExcursionObject, Long> {
    Optional<ExcursionObject> findById(Long id);
    Optional<ExcursionObject> findByExcursionId(Long id0);
}
