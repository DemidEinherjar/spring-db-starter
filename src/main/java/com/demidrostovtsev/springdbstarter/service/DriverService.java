package com.demidrostovtsev.springdbstarter.service;

import com.demidrostovtsev.springdbstarter.model.entity.Driver;
import com.demidrostovtsev.springdbstarter.model.dto.DriverDto;
import com.demidrostovtsev.springdbstarter.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public DriverDto create(DriverDto driverDto) {
        log.debug("DriverService creating new Driver with license : {}", driverDto.getLicense());
        Driver driver = driverRepository.save(modelMapper.map(driverDto, Driver.class));
        log.info("New Driver with license : {} added", driver.getLicense());
        return modelMapper.map(driver, DriverDto.class);
    }

    @Transactional
    public DriverDto read(String license) {
        log.debug("DriverService reading Driver with license : {}", license);
        Driver driver = driverRepository.findById(license).orElseThrow(() -> {
            log.error("Driver with license : {} NOT FOUND", license);
            return null;
        });
        log.info("Driver with license : {} found", license);
        return modelMapper.map(driver, DriverDto.class);
    }

    @Transactional
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

    @Transactional
    public void delete(String license) {
        log.debug("DriverService deleting Driver with license : {}", license);
        driverRepository.deleteById(license);
        log.info("Driver with license : {} deleted", license);
    }

    @Transactional
    public List<DriverDto> findAll(Integer pageNum){
        log.debug("DriverService reading all Drivers on page {}", pageNum.toString());
        return driverRepository.findAll(PageRequest.of(pageNum, 2))
                .stream()
                .map(driver -> modelMapper.map(driver, DriverDto.class))
                .collect(Collectors.toList());
    }

    /* 4) получить всех автолюбителей у которых есть машина */
    @Transactional
    public List<DriverDto> findDistinctByCarsIsNotNull() {
        log.debug("DriverService reading all Drivers who have a car");
        return driverRepository.findDistinctByCarsIsNotNull()
                .stream()
                .map(driver -> modelMapper.map(driver, DriverDto.class))
                .collect(Collectors.toList());
    }

    /* 5) получить всех автолюбителей у которых есть машина белого цвет */
    @Transactional
    public List<DriverDto> findByCarsColor(String color) {
        log.debug("DriverService reading all Drivers who have a {} car", color);
        return driverRepository.findByCarsColor(color)
                .stream()
                .map(driver -> modelMapper.map(driver, DriverDto.class))
                .collect(Collectors.toList());
    }

    /* 6) получить всех автолюбителей у которых нет машины */
    @Transactional
    public List<DriverDto> findByCarsIsNull() {
        log.debug("DriverService reading all Drivers who have not a car");
        return driverRepository.findByCarsIsNull()
                .stream()
                .map(driver -> modelMapper.map(driver, DriverDto.class))
                .collect(Collectors.toList());
    }

    /* 7) получить всех автолюбителей у которых номер 700 */
    @Transactional
    public List<DriverDto> findByCarsSrpContaining(String pattern) {
        log.debug("DriverService reading all Drivers who have a car with srp {}", pattern);
        return driverRepository.findByCarsSrpContaining(pattern)
                .stream()
                .map(driver -> modelMapper.map(driver, DriverDto.class))
                .collect(Collectors.toList());
    }

    /* 8) получить всех автолюбителей старше 30 лет */
    @Transactional
    public List<DriverDto> findByBirthdayBefore(Integer age) {
        log.debug("DriverService reading all Drivers who is older {}", age.toString());
        LocalDateTime ldt = LocalDateTime.now().minusYears(age);
        Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        return driverRepository.findByBirthdayBefore(date)
                .stream()
                .map(driver -> modelMapper.map(driver, DriverDto.class))
                .collect(Collectors.toList());
    }

    /* 11) получить всех автолюбителей у которых машины белого и красного цвета */
    @Transactional
    public List<DriverDto> findByCarsColorIn(String colorFilter) {
        log.debug("DriverService reading all Drivers who have a {} car", colorFilter);
        List<String> colors = new ArrayList<String>(List.of(colorFilter.split("&")));
        return driverRepository.findByCarsColorIn(colors)
                .stream()
                .map(driver -> modelMapper.map(driver, DriverDto.class))
                .collect(Collectors.toList());
    }
}
