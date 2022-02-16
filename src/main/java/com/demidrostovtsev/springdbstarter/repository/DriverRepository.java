package com.demidrostovtsev.springdbstarter.repository;

import com.demidrostovtsev.springdbstarter.model.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {
}
