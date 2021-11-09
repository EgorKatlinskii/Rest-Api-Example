package com.example.testtaskntiteam.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;


@Entity
@Table(name = "planets")
@Data
public class Planet {

    @Id
    @Column(name = "planet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planetIid;

    @Column(name = "planet_name")
    @NotNull(message = "Planet name is missing")
    @Size(min = 2,max = 25,message = "Minimum number of characters for the lord 's name : 3 , maximum : 25!")
    private String planetName;

    @ManyToOne()
    @JoinColumn(name="lord_id")
    @JsonIgnore
    private Lord lord;

    public Planet(String planetName){
        this.planetName=planetName;
    }

    public Planet() {

    }
}
