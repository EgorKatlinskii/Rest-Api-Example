package com.example.testtaskntiteam.Entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull(message = "Lord name is missing")
    @Size(min = 2, max = 25, message = "Minimum number of characters for the lord name : 2 , maximum : 25")
    private String lordName;

    @NotNull
    @Column(name = "lord_age")
    private int lordAge;

    @OneToMany(mappedBy = "lord")
    @ToString.Exclude
    private List<Planet> listPlanets;

    public Lord() {

    }

    public Lord(String lordName, int lordAge) {
        this.lordName = lordName;
        this.lordAge = lordAge;
    }

}
