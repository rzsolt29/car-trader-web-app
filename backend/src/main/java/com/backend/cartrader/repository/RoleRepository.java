package com.backend.cartrader.repository;

import com.backend.cartrader.model.ERole;
import com.backend.cartrader.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Boolean existsByName(ERole name);

    Optional<Role> findByName(ERole name);
}
