package com.backend.cartrader.repository;

import com.backend.cartrader.model.Car;
import com.backend.cartrader.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {

    List<Car> findAllByOwner(User owner);

}
