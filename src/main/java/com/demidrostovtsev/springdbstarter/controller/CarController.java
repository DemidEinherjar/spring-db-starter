package com.demidrostovtsev.springdbstarter.controller;

import com.demidrostovtsev.springdbstarter.model.dto.CarDto;
import com.demidrostovtsev.springdbstarter.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class CarController {

    private final CarService carService;

    @PostMapping(value = "/car")
    public ResponseEntity<CarDto> create(@RequestBody CarDto carDto) {
        log.debug("New POST request: new Car with srp {}", carDto.getSrp());
        return new ResponseEntity<>(carService.create(carDto), HttpStatus.OK);
    }

    @GetMapping(value = "/car/{vin}")
    public ResponseEntity<CarDto> read(@PathVariable(name = "vin") UUID vin) {
        log.debug("New GET request: get Car with vin : {}", vin.toString());
        return new ResponseEntity<>(carService.read(vin), HttpStatus.OK);
    }

    @PutMapping(value = "/car/{vin}")
    public ResponseEntity<?> update(@RequestBody CarDto carDto, @PathVariable(name = "vin") UUID vin) {
        log.debug("New PUT request: update Car with vin : {}", vin.toString());
        return new ResponseEntity<>(carService.update(vin, carDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/car/{vin}")
    public ResponseEntity<?> delete(@PathVariable(name = "vin") UUID vin) {
        log.debug("New DELETE request: delete Car with vin : {}", vin.toString());
        carService.delete(vin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/cars/{pageNum}")
    public ResponseEntity<List<CarDto>> findAll(@PathVariable(name = "pageNum") Integer pageNum) {
        log.debug("New GET request: get all Cars on page {}", pageNum.toString());
        return new ResponseEntity<>(carService.findAll(pageNum), HttpStatus.OK);
    }
}
