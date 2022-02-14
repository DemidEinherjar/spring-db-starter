package com.demidrostovtsev.springdbstarter.service;

import com.demidrostovtsev.springdbstarter.model.Car;
import com.demidrostovtsev.springdbstarter.model.CarDto;
import com.demidrostovtsev.springdbstarter.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarConverter carConverter;

    public CarDto create(CarDto carDto) {
       return carConverter.fromCarToCarDto(carRepository.save(carConverter.fromCarDtoToCar(carDto)));
    }

    public CarDto read(UUID vin) {
        return carConverter.fromCarToCarDto(carRepository.findById(vin).orElse(new Car()));
    }

    public CarDto update(UUID vin, CarDto carDto) {
        Car car = carRepository.findById(vin).orElseThrow(()->{
            return null;
        });
        car.setModel(carDto.getModel());
        car.setSrp(carDto.getSrp());
        car.setOwner(carDto.getOwner());
        car.setYi(carDto.getYi());
        car.setColor(carDto.getColor());

        return carConverter.fromCarToCarDto(carRepository.save(car));
    }

    public boolean delete(UUID vin) {
        if (carRepository.existsById(vin)) {
            carRepository.deleteById(vin);
            return true;
        } else {
            return false;
        }
    }

    public List<CarDto> findAll() {
        return carRepository.findAll()
                .stream()
                .map(carConverter::fromCarToCarDto)
                .collect(Collectors.toList());
    }
}
