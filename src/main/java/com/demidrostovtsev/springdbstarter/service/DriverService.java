package com.demidrostovtsev.springdbstarter.service;

import com.demidrostovtsev.springdbstarter.model.dto.CarDto;
import com.demidrostovtsev.springdbstarter.model.entity.Car;
import com.demidrostovtsev.springdbstarter.model.entity.Driver;
import com.demidrostovtsev.springdbstarter.model.dto.DriverDto;
import com.demidrostovtsev.springdbstarter.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;

    public DriverDto create(DriverDto driverDto) {
        log.debug("DriverService creating new Driver with license : {}", driverDto.getLicense());
        Driver driver = driverRepository.save(modelMapper.map(driverDto, Driver.class));
        log.info("New Driver with license : {} added", driver.getLicense());
        return modelMapper.map(driver, DriverDto.class);
    }

    public DriverDto read(String license) {
        log.debug("DriverService reading Driver with license : {}", license);
        Driver driver = driverRepository.findById(license).orElseThrow(() -> {
            log.error("Driver with license : {} NOT FOUND", license);
            return null;
        });
        log.info("Driver with license : {} found", license);
        return modelMapper.map(driver, DriverDto.class);
    }

    public DriverDto update(String license, DriverDto driverDto) {
        log.debug("DriverService updating Driver with license : {}", license);
        Driver driver = driverRepository.findById(license).orElseThrow(()->{
            log.error("Driver with license : {} NOT FOUND", license);
            return null;
        });
        driver.setLicense(driverDto.getLicense());
        driver.setName(driverDto.getName());
        driver.setBirthday(driverDto.getBirthday());

        log.info("Driver with license : {} updated", license);
        return modelMapper.map(driverRepository.save(driver), DriverDto.class);
    }

    public void delete(String license) {
        log.debug("DriverService deleting Driver with license : {}", license);
        driverRepository.deleteById(license);
        log.info("Driver with license : {} deleted", license);
    }

    public List<DriverDto> findAll(Integer pageNum){
        log.debug("DriverService reading all Drivers on page {}", pageNum.toString());;
        return driverRepository.findAll(PageRequest.of(pageNum, 2))
                .stream()
                .map(driver -> modelMapper.map(driver, DriverDto.class))
                .collect(Collectors.toList());
    }
}
