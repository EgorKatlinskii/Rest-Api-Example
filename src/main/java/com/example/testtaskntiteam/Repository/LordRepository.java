package com.example.testtaskntiteam.Repository;

import com.example.testtaskntiteam.Entity.Lord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LordRepository extends JpaRepository<Lord,Integer> {


    @Query(value = "SELECT * FROM lords  ORDER BY lord_age LIMIT  :amount ",nativeQuery = true)
    List<Lord>  getTheYoungestLords(@Param("amount") int amount);

}
