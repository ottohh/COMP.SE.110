package org.controllers;

import org.example.FlightService.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.resources.FlightOfferSearch;

@RestController
public class FlightController {
    private FlightService flightService;

    public FlightController() {
        this.flightService = new FlightService();
    }

    @GetMapping("/flights")
    public FlightOfferSearch[] getFlights(@RequestParam String origin, @RequestParam String destination, @RequestParam String departureDate, @RequestParam String returnDate, @RequestParam int adults) {
        try {
            return flightService.searchFlights(origin, destination, departureDate, returnDate, adults);
        } catch (Exception e) {
            e.printStackTrace();
            return new FlightOfferSearch[0];
        }
    }
}
