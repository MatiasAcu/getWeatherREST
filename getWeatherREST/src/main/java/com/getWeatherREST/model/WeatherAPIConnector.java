package com.getWeatherREST.model;

import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.stereotype.Service;

@Service
public class WeatherAPIConnector {

    private static final WeatherAPIConnector instance = new WeatherAPIConnector();

    private String key = "API_KEY";
    private String apiurl = "http://api.openweathermap.org/data/2.5/weather?q=";
    private String mode = "&mode=xml";
    private URL lastUrl;

    private WeatherAPIConnector() {
    }

    // Singleton//
    public static WeatherAPIConnector getInstance() {
        return instance;
    }

    // Gets json weather from city name//
    public JsonNode getWeather(String city, String unit, String lanCode) {
        try {
            lastUrl = new URL(apiurl + city + key + mode + "&units=" + unit + "&lang=" + lanCode);
            return updateWeather();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // Gets json weather from latitude and longitude//
    public JsonNode getWeather(String latitude, String longitude, String unit, String lanCode) {

        try {
            String city = convertCoordtoCity(latitude, longitude);
            this.lastUrl = new URL(apiurl + city + key + mode + "&units=" + unit + "&lang=" + lanCode);
            return updateWeather();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // Creates connection to WeatherAPI and returns json with weather data//
    public JsonNode updateWeather() {
        try {

            HttpURLConnection connection = (HttpURLConnection) lastUrl.openConnection();
            connection.setRequestMethod("GET");

            XmlMapper xmlMapper = new XmlMapper();

            return xmlMapper.readTree(connection.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private String convertCoordtoCity(String lat, String lon) {

        try {
            URL url = new URL(
                    "http://api.openweathermap.org/geo/1.0/reverse?lat=" + lat + "&lon=" + lon + "&limit=1" + key);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(connection.getInputStream()).get(0);

            return json.get("name").asText() + "," + json.get("country");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
