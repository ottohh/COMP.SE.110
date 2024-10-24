package org.example.AirportService;

import java.io.IOException;
import java.util.List;

import org.example.Utils.ApiClient;

public class AirportService {

    private static final String AIRPORT_API_URL = "https://www.flightsfrom.com/airports";
    private static final String ROUTES_API_URL = "https://www.flightsfrom.com/api/airport/";

    private ApiClient ApiClient;
    private AirportJsonParser airportJsonParser;

    public AirportService() {
        this.ApiClient = new ApiClient();
        this.airportJsonParser = new AirportJsonParser();
    }

    // Method to fetch the airport data
    public List<AirportDTO> FetchAirports() throws Exception {
        String jsonResponse = this.ApiClient.getJsonResponse(AIRPORT_API_URL);

        return airportJsonParser.parseAirportResponse(jsonResponse);
    }

    // Method to fetch airports from routes
    public List<AirportDTO> FetchAirportsFromRoutes(String airportCode, Integer day) throws IOException {
        String url = String.format("%s%s?days=day%d", ROUTES_API_URL, airportCode, day);

        String jsonResponse = this.ApiClient.getJsonResponse(url);

        return airportJsonParser.parseRoutesResponse(jsonResponse);
    }

    // Just for testing
    public static void main(String[] args) {
        try {
            AirportService airportService = new AirportService();
            List<AirportDTO> airports = airportService.FetchAirports();

            // Example usage: Fetch airports from routes for the first airport
            if (!airports.isEmpty()) {
                // Print the first five airports
                System.out.println("First five airports:");
                for (int i = 0; i < 5; i++) {
                    System.out.println(airports.get(i));
                }

                AirportDTO firstAirport = airports.get(0);
                List<AirportDTO> routeAirports = airportService.FetchAirportsFromRoutes(firstAirport.getIATA(), 1);

                System.out.println("First five routes from" + firstAirport.getName() + ":");
                // Print the fetched airports
                for (AirportDTO airport : routeAirports) {
                    System.out.println(airport);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
