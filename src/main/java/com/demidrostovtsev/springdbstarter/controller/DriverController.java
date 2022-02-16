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
}
