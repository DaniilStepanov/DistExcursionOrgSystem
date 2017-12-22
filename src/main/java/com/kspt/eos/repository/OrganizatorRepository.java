package com.kspt.eos.repository;

import com.kspt.eos.entity.Organizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizatorRepository extends JpaRepository<Organizator, Long> {
    Optional<Organizator> findById(Long id);
    Optional<Organizator> findByExcursionId(Long id);
    Optional<Organizator> findByExcursionName(String name);
    Optional<Organizator> findByUserLogin(String login);
}
