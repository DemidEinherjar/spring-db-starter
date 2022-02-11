package com.demidrostovtsev.springdbstarter.repository;

import com.demidrostovtsev.springdbstarter.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
}
