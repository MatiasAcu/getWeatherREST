package com.getWeatherREST.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.getWeatherREST.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    WeatherMapper weatherMapper;

    @Autowired
    DBManager dbManager;
    
    @GetMapping(value = "/weather", params={"lat","lon","unit","langCode"})
    public String getWeatherByCoords(@RequestParam(name = "lat") String lat, @RequestParam(name = "lon") String lon,
            @RequestParam(name = "unit", defaultValue = "metric") String unit,
            @RequestParam(name = "langCode", defaultValue = "es") String langCode) {

        return weatherMapper.getWeather(lat, lon, unit, langCode);
    }

    @GetMapping(value="/weather", params={"cityName","unit","langCode"})
    public String getWeatherByLocation(HttpServletResponse response, @RequestParam(name = "cityName") String cityName,
            @RequestParam(name = "unit", defaultValue = "metric") String unit,
            @RequestParam(name = "langCode", defaultValue = "es") String langCode) {
        return weatherMapper.getWeather(cityName, unit, langCode);

    }

    //Support for a search location autocomplete//
    @GetMapping("/locations/like")
    public String[] getLocationsLike(HttpServletResponse response, @RequestParam(name = "cityName") String cityName) {
        if (cityName.length() < 3) {
            try {
                response.sendError(406, "City name has to be 3 characters lenght at least");
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            return dbManager.getLocationsLike(cityName);
        }
        return null;

    }

   

}
