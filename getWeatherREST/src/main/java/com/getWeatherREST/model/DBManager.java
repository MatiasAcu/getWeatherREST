package com.getWeatherREST.model;

import com.getWeatherREST.model.repositories.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBManager {

    @Autowired
    LocationRepository locationRepository;

    public DBManager() {
    }

    public String[] getLocationsLike(String cityName) {
        return locationRepository.findLocationsLike(cityName);
    }

}
