package com.getWeatherREST.model.repositories;


import com.getWeatherREST.model.entities.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query(value = "SELECT TOP 8 city_name FROM mydb.[location] where [city_name] like CONCAT(:cityname,'%') COLLATE SQL_Latin1_General_CP1253_CI_AI ORDER BY len(city_name)", nativeQuery = true)
    String[] findLocationsLike(@Param("cityname") String cityName);

}
