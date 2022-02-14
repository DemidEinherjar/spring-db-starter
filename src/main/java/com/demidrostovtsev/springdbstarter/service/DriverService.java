package com.demidrostovtsev.springdbstarter.service;

import com.demidrostovtsev.springdbstarter.model.Driver;
import com.demidrostovtsev.springdbstarter.model.DriverDto;
import com.demidrostovtsev.springdbstarter.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final DriverConverter driverConverter;

    public DriverDto create(DriverDto driverDto) {
        return driverConverter.fromDriverToDriverDto(driverRepository.save(driverConverter.fromDriverDtoToDriver(driverDto)));
    }

    public DriverDto read(String license) {
        return driverConverter.fromDriverToDriverDto(driverRepository.findById(license).orElse(new Driver()));
    }

    public DriverDto update(String license, DriverDto driverDto) {
        Driver driver = driverRepository.findById(license).orElseThrow(()->{
            return null;
        });
        driver.setLicense(driverDto.getLicense());
        driver.setName(driverDto.getName());
        driver.setBirthday(driverDto.getBirthday());

        return driverConverter.fromDriverToDriverDto(driverRepository.save(driver));
    }

    public boolean delete(String license) {
        if (driverRepository.existsById(license)) {
            driverRepository.deleteById(license);
            return true;
        } else {
            return false;
        }
    }

    public List<DriverDto> findAll(){
        return driverRepository.findAll()
                .stream()
                .map(driverConverter::fromDriverToDriverDto)
                .collect(Collectors.toList());
    }
}
