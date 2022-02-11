package com.demidrostovtsev.springdbstarter.service;

import com.demidrostovtsev.springdbstarter.model.Driver;
import com.demidrostovtsev.springdbstarter.model.DriverDto;

public class DriverConverter {

    public Driver fromDriverDtoToDriver(DriverDto driverDto) {

        Driver driver = new Driver();
        driver.setLicense(driverDto.getLicense());
        driver.setName(driverDto.getName());
        driver.setBirthday(driverDto.getBirthday());
        return driver;
    }

    public DriverDto fromDriverToDriverDto(Driver driver) {

        DriverDto driverDto = new DriverDto();
        driverDto.setLicense(driver.getLicense());
        driverDto.setName(driver.getName());
        driverDto.setBirthday(driver.getBirthday());
        return driverDto;
    }
}
