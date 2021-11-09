package com.example.testtaskntiteam.Repository;

import com.example.testtaskntiteam.Entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer> {


}
