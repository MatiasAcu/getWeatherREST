package com.getWeatherREST.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Entity
@Table(name = "location", schema = "mydb")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLocation;
    @Column(nullable = false, unique = true)
    private String cityName;

    public Location() {
    }

    public Location(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String toString() {
        try {
            return new JsonMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
