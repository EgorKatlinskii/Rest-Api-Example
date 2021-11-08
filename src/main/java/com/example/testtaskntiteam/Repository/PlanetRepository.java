package com.example.testtaskntiteam.Repository;

import com.example.testtaskntiteam.Entity.Lord;
import com.example.testtaskntiteam.Entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanetRepository extends JpaRepository<Planet,Integer> {


}
