package com.demidrostovtsev.springdbstarter.model.dto;

import com.demidrostovtsev.springdbstarter.model.entity.Driver;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CarDto {

    private UUID vin;
    private String model;
    private String srp;
    private Driver owner;
    private Integer yi;
    private String color;
}
