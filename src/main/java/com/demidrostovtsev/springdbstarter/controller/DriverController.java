package com.demidrostovtsev.springdbstarter.controller;

import com.demidrostovtsev.springdbstarter.model.Driver;
import com.demidrostovtsev.springdbstarter.model.DriverDto;
import com.demidrostovtsev.springdbstarter.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/driver")
public class DriverController {

    private final DriverService driverService;

    @PostMapping(value = "/new")
    public ResponseEntity<DriverDto> create(@RequestBody DriverDto driverDto) {
        return new ResponseEntity<>(driverService.create(driverDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{license}")
    public ResponseEntity<DriverDto> read(@PathVariable(name = "license") String license) {
        final DriverDto driverDto = driverService.read(license);
        return driverDto != null ? new ResponseEntity<>(driverDto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{license}/update")
    public ResponseEntity<?> update(@RequestBody DriverDto driverDto, @PathVariable(name = "license") String license) {
        return new ResponseEntity<>(driverService.update(license, driverDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{license}/delete")
    public ResponseEntity<?> delete(@PathVariable(name = "license") String license) {
        return driverService.delete(license) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<DriverDto>> findALL() {
        return new ResponseEntity<>(driverService.findAll(), HttpStatus.OK);
    }
}
