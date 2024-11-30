package org.controllers;

import java.util.ArrayList;
import java.util.List;

import org.example.FlightService.FlightDTO;
import org.example.FlightService.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.amadeus.resources.FlightOfferSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightController {
    private FlightService flightService;
    
    public FlightController() {
        this.flightService = new FlightService();
    }

    @GetMapping("/flights")
    public List<FlightDTO> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String departureDate,
            @RequestParam(required = false) String returnDate,
            @RequestParam int adults) {
                try{
                    return flightService.searchFlights(origin, destination, departureDate, returnDate, adults);
                }
                
                catch(Exception e){
                    e.printStackTrace();
                    return new ArrayList<>();
                }
                
    }
}
