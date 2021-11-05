package com.example.testtaskntiteam.Entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @Column(name = "planet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer planetIid;


    @Column(name = "planet_name")
    @NotNull
    private String planetName;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @NotNull
    private Lord lord;

}
