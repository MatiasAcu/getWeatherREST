package com.getWeatherREST.model;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//
// Converts the weather info fetched from the WeatherAPI to a JSON like:
// {"date": DATE,
//  "city":{ "name": string, "country": string},
//  "temperature":{ "value": number, "unit": string},
//  "humidity":{ "value":number, "unit": string},
//  "climate":{ "value": string, "id": number},
//  "pressure":{ "value": number,"unit": string},
//  "cloudiness": number,
//  "wind":{ "value": number, "unit": string, "name": string}}
//

@Service
public class WeatherMapper {

    @Autowired
    WeatherAPIConnector weatherAPIConnector = WeatherAPIConnector.getInstance();

    JsonMapper jsonMapper = new JsonMapper();

    public WeatherMapper() {
    }

    public String getWeather(String cityName, String unit, String lanCode) {
        JsonNode weather = weatherAPIConnector.getWeather(cityName, unit, lanCode);
        HashMap<String, JsonNode> map = new HashMap<>();

        ObjectNode climate = jsonMapper.createObjectNode();
        climate.set("value", weather.get("weather").get("value"));
        climate.set("id", weather.get("weather").get("number"));
        map.put("climate", climate);

        map.put("temperature", weather.get("feels_like"));
        map.put("wind", weather.get("wind").get("speed"));
        map.put("humidity", weather.get("humidity"));
        map.put("pressure", weather.get("pressure"));

        ObjectNode city = jsonMapper.createObjectNode();
        city.set("name", weather.get("city").get("name"));
        city.set("country", weather.get("city").get("country"));

        map.put("city", city);
        map.put("date", weather.get("lastupdate").get("value"));
        return this.toString(map);

    }

    public String getWeather(String latitude, String longitude, String unit, String lanCode) {

        JsonNode weather = weatherAPIConnector.getWeather(latitude, longitude, unit, lanCode);
        HashMap<String, JsonNode> map = new HashMap<>();

        ObjectNode climate = jsonMapper.createObjectNode();
        climate.set("value", weather.get("weather").get("value"));
        climate.set("id", weather.get("weather").get("id"));
        map.put("climate", climate);

        map.put("temperature", weather.get("feels_like"));
        map.put("wind", weather.get("wind").get("speed"));
        map.put("humidity", weather.get("humidity"));
        map.put("pressure", weather.get("pressure"));
        map.put("cloudiness", weather.get("clouds").get("value"));

        ObjectNode city = jsonMapper.createObjectNode();
        city.set("name", weather.get("city").get("name"));
        city.set("country", weather.get("city").get("country"));

        map.put("city", city);
        map.put("date", weather.get("lastupdate").get("value"));
        return this.toString(map);

    }

    public String toString(HashMap<String, JsonNode> map) {
        try {
            return new JsonMapper().writeValueAsString(map).replace("\\", "");
        } catch (JsonProcessingException e) {
            return null;
        }

    }

}