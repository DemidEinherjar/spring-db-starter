package com.demidrostovtsev.springdbstarter.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @Column(name = "license")
    private String license;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthday;
}
