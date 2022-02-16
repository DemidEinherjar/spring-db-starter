package com.demidrostovtsev.springdbstarter.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "drivers")
public class Driver implements Serializable {

    @Id
    @Column(name = "license")
    private String license;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthday;
}
