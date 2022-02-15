package com.demidrostovtsev.springdbstarter.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CarDto {

    private UUID vin;
    private String model;
    private String srp;
    private String owner;
    private Integer yi;
    private String color;
}
