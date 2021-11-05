package com.example.testtaskntiteam.Repository;

import com.example.testtaskntiteam.Entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet,Integer> {
}
