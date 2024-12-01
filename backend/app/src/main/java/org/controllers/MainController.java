/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.controllers;

/**
 *
 * @author ottop
 */
import com.amadeus.resources.FlightOfferSearch;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.AirportService.AirportDTO;
import org.example.AirportService.AirportService;
import org.example.FlightService.FlightDTO;
import org.example.WeatherService.ForecastDTO;
import org.example.WeatherService.WeatherService;
import org.example.FlightService.FlightService;

@RestController
public class MainController {

    private AirportService airportService = new AirportService();
    private FlightService flightService = new FlightService();
    int pageSize=5;
    
    @GetMapping("/getFlights")
    public response getFlights(String originIATA,int page) throws IOException {
   
        LocalDate today = LocalDate.now();
        
        int dayNumber = today.getDayOfWeek().getValue();
        List<AirportDTO> airports = airportService.FetchAirportsFromRoutes(originIATA, dayNumber);

        var paginatedAirports = getPaginatedAirports(airports,page);
        
        var response = new response();
        response.pagecount=airports.size()/pageSize+1;
        response.data=new ArrayList<Data>();
        
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate returnDate = LocalDate.now().plusDays(7);
  
        for(AirportDTO airport:paginatedAirports){
            var data=new Data();
 
            
            data.airport=airport;
            
            try {
                var flights= flightService.searchFlights(originIATA, airport.getIATA(), today.format(formatter), returnDate.format(formatter), 1);
                
                data.price=Float.toString(flights.get(0).getTotalPrice());
                data.flight=flights.get(0); //Get first FlightDTO object from list
            } catch (Exception e) {
                e.printStackTrace();
            } 
           WeatherService service = new WeatherService(Double.toString(airport.getLatitude()), Double.toString(airport.getLongitude()));
    
            if(service.LoadForeCasts())data.weather=service.GetForecastForDate(java.time.LocalDate.now());
            response.data.add(data);
  
        }
        
                
  
        return response;
    }
    
    private List<AirportDTO> getPaginatedAirports(List<AirportDTO> airports, int pageNumber) {
        int startIndex = (pageNumber - 1) * pageSize; // Calculate the start index
        int endIndex = Math.min(startIndex + pageSize, airports.size()); // Calculate the end index

        // Validate indices and return the sublist
        if (startIndex >= airports.size() || startIndex < 0) {
            return new ArrayList<AirportDTO>(); // Return an empty list if the page number is out of range
        }

        return airports.subList(startIndex, endIndex);
    }
    
    private class response {
        public List<Data> data;
        public int pagecount;
    }
    
    private class Data{
        //public FlightOfferSearch flight;
        public String price;
        public FlightDTO flight;
        public ForecastDTO weather;
        public AirportDTO airport;
    }
    
    
}