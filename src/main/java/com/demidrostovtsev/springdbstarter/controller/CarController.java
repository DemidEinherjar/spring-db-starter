package com.demidrostovtsev.springdbstarter.controller;

import com.demidrostovtsev.springdbstarter.model.CarDto;
import com.demidrostovtsev.springdbstarter.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/car")
public class CarController {

    private final CarService carService;

    @PostMapping(value = "/new")
    public ResponseEntity<CarDto> create(@RequestBody CarDto carDto) {
        return new ResponseEntity<>(carService.create(carDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{vin}")
    public ResponseEntity<CarDto> read(@PathVariable(name = "vin") UUID vin) {
        final CarDto carDto = carService.read(vin);
        return carDto != null ? new ResponseEntity<>(carDto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{vin}/update")
    public ResponseEntity<?> update(@RequestBody CarDto carDto, @PathVariable(name = "vin") UUID vin) {
        return new ResponseEntity<>(carService.update(vin, carDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{vin}/delete")
    public ResponseEntity<?> delete(@PathVariable(name = "vin") UUID vin) {
        return carService.delete(vin) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<CarDto>> findAll() {
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }
}
