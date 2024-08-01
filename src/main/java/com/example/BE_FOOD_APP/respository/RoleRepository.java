package com.example.BE_FOOD_APP.respository;

import com.example.BE_FOOD_APP.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String role);
    Optional<Role> findByName(String role);
}
