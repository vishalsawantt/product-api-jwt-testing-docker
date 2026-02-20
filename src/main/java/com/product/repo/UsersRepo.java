package com.product.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.entity.Users;

public interface UsersRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
