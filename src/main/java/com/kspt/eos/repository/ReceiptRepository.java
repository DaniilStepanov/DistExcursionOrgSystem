package com.kspt.eos.repository;

import com.kspt.eos.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Optional<Receipt> findById(Long id);
    Optional<Receipt> findByExcursionId(Long id);
    Optional<Receipt> findByExcursionName(String name);
}
