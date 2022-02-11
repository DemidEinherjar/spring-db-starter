package com.demidrostovtsev.springdbstarter.controller;

import com.demidrostovtsev.springdbstarter.model.Driver;
import com.demidrostovtsev.springdbstarter.model.DriverDto;
import com.demidrostovtsev.springdbstarter.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/driver")
public class DriverController {

    DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping(value = "/new")
    public ResponseEntity<?> create(@RequestBody Driver driver) {
        driverService.create(driver);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{license}")
    public ResponseEntity<DriverDto> read(@PathVariable(name = "license") String license) {
        final DriverDto driverDto = driverService.read(license);
        return driverDto != null ? new ResponseEntity<>(driverDto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{license}/update")
    public ResponseEntity<?> update(@RequestBody Driver driver, @PathVariable(name = "license") String license) {
        return driverService.update(driver, license) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{license}/delete")
    public ResponseEntity<?> delete(@PathVariable(name = "license") String license) {
        return driverService.delete(license) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Driver>> getALL() {
         final List<Driver> drivers = driverService.findAll();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }
}
