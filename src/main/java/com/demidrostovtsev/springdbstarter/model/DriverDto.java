package com.demidrostovtsev.springdbstarter.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DriverDto {

    private String license;
    private String name;
    private Date birthday;
}
