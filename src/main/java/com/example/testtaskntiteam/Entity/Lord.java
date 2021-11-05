package com.example.testtaskntiteam.Entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "lords")
public class Lord {

    @Id
    @Column(name = "lord_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lordId;

    @Column(name = "lord_name")
    @NotNull
    private String lordName;

    @Column(name = "lord_age")
    @NotNull
    private int lordAge;

    @OneToMany(mappedBy = "lord", cascade = CascadeType.ALL)
    private List<Planet> listPlanets;

}
