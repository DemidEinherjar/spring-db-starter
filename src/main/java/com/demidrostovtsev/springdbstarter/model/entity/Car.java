package com.demidrostovtsev.springdbstarter.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "vin")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID vin;

    @Column(name = "model")
    private String model;

    @Column(name = "srp")
    private String srp;

    @JoinColumn(name = "owner")
    @ManyToOne
    private Driver owner;

    @Column(name = "yi")
    private Integer yi;

    @Column(name = "color")
    private String color;
}
