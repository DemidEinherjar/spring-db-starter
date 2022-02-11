package com.demidrostovtsev.springdbstarter.service;

import com.demidrostovtsev.springdbstarter.model.Driver;
import com.demidrostovtsev.springdbstarter.model.DriverDto;
import com.demidrostovtsev.springdbstarter.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    private DriverConverter driverConverter;

    public void create(Driver driver) {
        driverRepository.save(driver);
    }

    @Transactional
    public DriverDto read(String license) {
        Driver driver = driverRepository.getById(license);
        return driverConverter.fromDriverToDriverDto(driver);
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

    public List<Driver> findAll(){
        return new ArrayList<>(driverRepository.findAll());
    }
}
