package com.demidrostovtsev.springdbstarter.service;

import com.demidrostovtsev.springdbstarter.model.Car;
import com.demidrostovtsev.springdbstarter.model.CarDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CarConverter {

    private final ModelMapper modelMapper;

    public Car fromCarDtoToCar(CarDto carDto) {
        return modelMapper.map(carDto, Car.class);
    }

    public CarDto fromCarToCarDto(Car car) {
        return modelMapper.map(car, CarDto.class);
    }
}
