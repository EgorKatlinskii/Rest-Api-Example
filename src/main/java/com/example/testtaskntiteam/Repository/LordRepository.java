package com.example.testtaskntiteam.Repository;

import com.example.testtaskntiteam.Entity.Lord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LordRepository extends JpaRepository<Lord, Integer> {


    @Query(value = "SELECT * FROM lords  ORDER BY lord_age LIMIT  :amount ", nativeQuery = true)
    List<Lord> getTheYoungestLords(@Param("amount") int amount);

    @Query(value = "select * from lords   where lord_id not in" +
            "(select lord_id from lords where lord_id in" +
            "(select lord_id  from planets ))", nativeQuery = true)
    List<Lord> getIdlers();

}
