package com.example.testtaskntiteam.Entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "planets")
@Data
public class Planet {

    @Id
    @Column(name = "planet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer planetIid;


    @Column(name = "planet_name")
    @NotNull
    private String planetName;

}
