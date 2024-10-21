package org.example.AirportService;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
// okhttp used because there was issues default HttpClient where API returned 403 forbidden 
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// TODO new improved design to be used ... 
// TODO there is still some flakines with the API, sometimes it returns 403 forbidden
public class AirportService {

    private static final String AIRPORT_API_URL = "https://www.flightsfrom.com/airports";
    private static final String ROUTES_API_URL = "https://www.flightsfrom.com/api/airport/";

    // Method to fetch the airport data
    public List<AirportDTO> fetchAirports() throws Exception {
        String jsonResponse = getJsonResponse(AIRPORT_API_URL);
        Gson gson = new Gson();

        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonObject response = jsonObject.getAsJsonObject("response");

        AirportResponse airportResponse = gson.fromJson(response, AirportResponse.class);
        return airportResponse.getAirports();
    }

    // Method to fetch airports from routes
    public List<AirportDTO> fetchAirportsFromRoutes(String airportCode, Integer day) throws IOException {
        String jsonResponse = getRoutesJsonResponse(airportCode, day);

        Gson gson = new Gson();

        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonObject response = jsonObject.getAsJsonObject("response");

        // Extract airport details from the routes response
        List<AirportDTO> airports = new ArrayList<>();

        // Get routes object from response
        if (response.has("routes")) {
            JsonObject routes = response.getAsJsonObject("routes");

            // Loop through each route entry
            for (String key : routes.keySet()) {
                JsonObject routeObj = routes.getAsJsonObject(key);
                JsonObject airportObj = routeObj.getAsJsonObject("airport");

                // Parse the airport object and add it to the list
                AirportDTO airport = gson.fromJson(airportObj, AirportDTO.class);
                airports.add(airport);
            }
        }

        return airports;

    }

    private String getRoutesJsonResponse(String airportCode, Integer day) throws IOException {
        String url = String.format("%s%s?days=day%d", ROUTES_API_URL, airportCode, day);

        System.out.println(url);

        return getJsonResponse(url);
    }

    public String getJsonResponse(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);

            return response.body().string();
        }
    }

    // Just for testing
    public static void main(String[] args) {
        try {
            AirportService airportService = new AirportService();
            List<AirportDTO> airports = airportService.fetchAirports();

            // Example usage: Fetch airports from routes for the first airport
            if (!airports.isEmpty()) {
                // Print the first five airports
                System.out.println("First five airports:");
                for (int i = 0; i < 5; i++) {
                    System.out.println(airports.get(i));
                }

                AirportDTO firstAirport = airports.get(0);
                List<AirportDTO> routeAirports = airportService.fetchAirportsFromRoutes(firstAirport.getIATA(), 1);

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
