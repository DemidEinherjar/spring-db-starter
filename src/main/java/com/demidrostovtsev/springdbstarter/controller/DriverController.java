package com.demidrostovtsev.springdbstarter.controller;

import com.demidrostovtsev.springdbstarter.model.Driver;
import com.demidrostovtsev.springdbstarter.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/driver")
public class DriverController {

    DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping(name = "/new")
    public ResponseEntity<?> create(@RequestBody Driver driver) {
        driverService.create(driver);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

/*    @GetMapping(name = "/{license}")
    public ResponseEntity<Driver> read(@PathVariable String license) {
        final Driver driver = driverService.read(license);
        return driver != null ? new ResponseEntity<>(driver, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    @PutMapping(name = "/{license}/update")
    public ResponseEntity<?> update(@RequestBody Driver driver, @PathVariable String license) {
        return driverService.update(driver, license) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(name = "/{license}/delete")
    public ResponseEntity<?> delete(@PathVariable String license) {
        return driverService.delete(license) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(name = "/test")
    public ResponseEntity<?> readAll() {
        // final List<Driver> drivers = driverService.readAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
