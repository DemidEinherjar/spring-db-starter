package com.demidrostovtsev.springdbstarter.service;

import com.demidrostovtsev.springdbstarter.model.Driver;
import com.demidrostovtsev.springdbstarter.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public void create(Driver driver) {
        driverRepository.save(driver);
    }

    public Driver read(String license) {
        return driverRepository.getById(license);
    }

    public boolean update(Driver driver, String license) {
        if (driverRepository.existsById(license)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(String license) {
        if (driverRepository.existsById(license)) {
            driverRepository.deleteById(license);
            return true;
        } else {
            return false;
        }
    }

    public List<Driver> readAll(){
        return new ArrayList<>(driverRepository.findAll());
    }
}
