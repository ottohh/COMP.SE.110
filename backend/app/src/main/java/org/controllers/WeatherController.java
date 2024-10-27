package org.controllers;

import org.example.WeatherService.ForecastDTO;
import org.example.WeatherService.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    /*
     * This GET route takes lat and long and returns the weather data for that
     * location
     * 
     */
    @GetMapping("/location")
    public ResponseEntity<ForecastDTO> getWeatherByLocation(@RequestParam("lat") String latitude,
            @RequestParam("long") String longitude) {
        try {
            WeatherService service = new WeatherService(latitude, longitude);
            service.LoadForeCasts();

            ForecastDTO forecast = service.GetForecastForDate(
                    java.time.LocalDate.now());

            // TODO @ottop: Serialization of ForecastDTO to JSON doesn't work
            return ResponseEntity.ok(forecast);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
