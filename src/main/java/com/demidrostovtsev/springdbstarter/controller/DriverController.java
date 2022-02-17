package com.demidrostovtsev.springdbstarter.controller;

import com.demidrostovtsev.springdbstarter.model.dto.DriverDto;
import com.demidrostovtsev.springdbstarter.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class DriverController {

    private final DriverService driverService;

    @PostMapping(value = "/driver")
    public ResponseEntity<DriverDto> create(@RequestBody DriverDto driverDto) {
        log.debug("New POST request: new Driver with license number {}", driverDto.getLicense());
        return new ResponseEntity<>(driverService.create(driverDto), HttpStatus.OK);
    }

    @GetMapping(value = "/driver/{license}")
    public ResponseEntity<DriverDto> read(@PathVariable(name = "license") String license) {
        log.debug("New GET request: get Driver with license : {}", license);
        return new ResponseEntity<>(driverService.read(license), HttpStatus.OK);
    }

    @PutMapping(value = "/driver/{license}")
    public ResponseEntity<?> update(@RequestBody DriverDto driverDto, @PathVariable(name = "license") String license) {
        log.debug("New PUT request: update Driver with license : {}", license);
        return new ResponseEntity<>(driverService.update(license, driverDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/driver/{license}")
    public ResponseEntity<?> delete(@PathVariable(name = "license") String license) {
        log.debug("New DELETE request: delete Driver with license : {}", license);
        driverService.delete(license);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/drivers/{pageNum}")
    public ResponseEntity<List<DriverDto>> findALL(@PathVariable(name = "pageNum") Integer pageNum) {
        log.debug("New GET request: get all Driver on page {}", pageNum.toString());
        return new ResponseEntity<>(driverService.findAll(pageNum), HttpStatus.OK);
    }

    /* 4) получить всех автолюбителей у которых есть машина */
    @GetMapping(value = "/drivers/with-a-car")
    public ResponseEntity<List<DriverDto>> findDistinctByCarsIsNotNull() {
        log.debug("New GET request: get all Driver who have a car");
        return new ResponseEntity<>(driverService.findDistinctByCarsIsNotNull(), HttpStatus.OK);
    }

    /* 5) получить всех автолюбителей у которых есть машина белого цвет */
    @GetMapping(value = "/drivers/car/color={color}")
    public ResponseEntity<List<DriverDto>> findByCarColor(@PathVariable(name = "color") String color) {
        log.debug("New GET request: get all Driver who have {} a car", color);
        return new ResponseEntity<>(driverService.findByCarsColor(color), HttpStatus.OK);
    }

    /* 6) получить всех автолюбителей у которых нет машины */
    @GetMapping(value = "/drivers/without-а-car")
    public ResponseEntity<List<DriverDto>> findByCarsIsNull() {
        log.debug("New GET request: get all Driver who have not a car");
        return new ResponseEntity<>(driverService.findByCarsIsNull(), HttpStatus.OK);
    }

    /*7) получить всех автолюбителей у которых номер 700 */
    @GetMapping(value = "/drivers/car/srp={pattern}")
    public ResponseEntity<List<DriverDto>> findByCarsSrpContaining(@PathVariable(name = "pattern") String pattern) {
        log.debug("New GET request: get all Driver who have a car with srp {}", pattern);
        return new ResponseEntity<>(driverService.findByCarsSrpContaining(pattern), HttpStatus.OK);
    }

    /* 8) получить всех автолюбителей старше 30 лет */
    @GetMapping(value = "/drivers/older={age}")
    public ResponseEntity<List<DriverDto>> findByBirthdayBefore(@PathVariable(name = "age") Integer age) {
        log.debug("New GET request: get all Driver who is older {}", age.toString());
        return new ResponseEntity<>(driverService.findByBirthdayBefore(age), HttpStatus.OK);
    }

    /* 11) получить всех автолюбителей у которых машины белого и красного цвета */
    @GetMapping(value = "/drivers/car/colors={colorFilter}")
    public ResponseEntity<List<DriverDto>> findByCarsColorIn(@PathVariable(name = "colorFilter") String colorFilter) {
        log.debug("New GET request: get all Driver who have a {} car", colorFilter);
        return new ResponseEntity<>(driverService.findByCarsColorIn(colorFilter), HttpStatus.OK);
    }
}
