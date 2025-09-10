package com.spincoders.cropmaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spincoders.cropmaster.model.Weather;
import com.spincoders.cropmaster.service.WeatherService;

@RestController
@RequestMapping("/weather")
@CrossOrigin(origins = "http://localhost:5173")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/addNew")
    public String add(@RequestBody Weather Weather) {
        weatherService.saveWeather(Weather);
        return "New Weather is added";
    }

    @GetMapping("/getDetails/{farmlandID}")
    public ResponseEntity<Weather> getSoilByFarmlandID(@PathVariable int farmlandID) {
        Weather weather = weatherService.getWeatherinfoById(farmlandID);
        if (weather != null) {
            return ResponseEntity.ok(weather);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
