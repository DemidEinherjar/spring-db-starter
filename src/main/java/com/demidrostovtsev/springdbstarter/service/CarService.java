package com.demidrostovtsev.springdbstarter.service;

import com.demidrostovtsev.springdbstarter.model.Car;
import com.demidrostovtsev.springdbstarter.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    void create(Car car) {
        carRepository.save(car);
    }

    public Car read(UUID vin) {
        return carRepository.getById(vin);
    }

    public boolean update(Car car, UUID vin) {
        if (carRepository.existsById(vin)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(UUID vin) {
        if (carRepository.existsById(vin)) {
            carRepository.deleteById(vin);
            return true;
        } else {
            return false;
        }
    }
}
