package com.kspt.eos.repository;

import com.kspt.eos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByLogin(String login);
    Optional<User> findByName(String name);
}
