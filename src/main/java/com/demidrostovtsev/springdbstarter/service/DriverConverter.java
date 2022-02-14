package com.demidrostovtsev.springdbstarter.service;

import com.demidrostovtsev.springdbstarter.model.Driver;
import com.demidrostovtsev.springdbstarter.model.DriverDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DriverConverter {

    private final ModelMapper modelMapper;

    public Driver fromDriverDtoToDriver(DriverDto driverDto) {
        return modelMapper.map(driverDto, Driver.class);
    }

    public DriverDto fromDriverToDriverDto(Driver driver) {
        return modelMapper.map(driver, DriverDto.class);
    }
}
