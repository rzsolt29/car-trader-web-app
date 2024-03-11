package com.backend.cartrader.repository;

import com.backend.cartrader.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findAllByUserId(Integer id);
}
