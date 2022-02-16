package com.demidrostovtsev.springdbstarter.service;

import com.demidrostovtsev.springdbstarter.model.entity.Car;
import com.demidrostovtsev.springdbstarter.model.dto.CarDto;
import com.demidrostovtsev.springdbstarter.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public CarDto create(CarDto carDto) {
        log.debug("CarService creating new Car with srp: {}", carDto.getSrp());
        Car car = carRepository.save(modelMapper.map(carDto, Car.class));
        log.info("New Car with vin: {} srp: {} added", car.getVin().toString(), car.getSrp());
        return modelMapper.map(car, CarDto.class);
    }

    @Transactional
    public CarDto read(UUID vin) {
        log.debug("CarService reading Car with vin: {}", vin.toString());
        Car car = carRepository.findById(vin).orElseThrow(() -> {
            log.error("Car with vin : {} NOT FOUND", vin.toString());
            return null;
        });
        log.info("Car with vin : {} found", vin.toString());
        return modelMapper.map(car, CarDto.class);
    }

    @Transactional
    public CarDto update(UUID vin, CarDto carDto) {
        log.debug("CarService updating Car with vin: {}", vin.toString());
        Car car = carRepository.findById(vin).orElseThrow(() -> {
            log.error("Car with vin : {} NOT FOUND", vin.toString());
            return null;
        });
        car.setModel(carDto.getModel());
        car.setSrp(carDto.getSrp());
        car.setOwner(carDto.getOwner());
        car.setYi(carDto.getYi());
        car.setColor(carDto.getColor());

        log.info("Car with vin : {} updated", vin.toString());
        return modelMapper.map(carRepository.save(car), CarDto.class);
    }

    @Transactional
    public void delete(UUID vin) {
        log.debug("CarService deleting Car with vin: {}", vin.toString());
        carRepository.deleteById(vin);
        log.info("Car with vin : {} deleted", vin.toString());
    }

    @Transactional
    public List<CarDto> findAll(Integer pageNum) {
        log.debug("CarService reading all Cars on page {}", pageNum.toString());;
        return carRepository.findAll(PageRequest.of(pageNum, 2))
                .stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
   }
}
