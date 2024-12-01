package org.example.AirportService;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AirportJsonParser {
    private Gson gson;

    public AirportJsonParser() {
        this.gson = new Gson();
    }

    public List<AirportDTO> parseAirportResponse(String jsonResponse) {
        JsonObject response = getResponseObject(jsonResponse);

        AirportResponse airportResponse = this.gson.fromJson(response, AirportResponse.class);

        return airportResponse.getAirportsWhichHaveRoutes();
    }

    public List<AirportDTO> parseRoutesResponse(String jsonResponse) {
        JsonObject response = getResponseObject(jsonResponse);

        // Extract airport details from the routes response
        List<AirportDTO> airports = new ArrayList<>();

        // Get routes object from response
        if (response.has("routes")) {
            JsonElement routesElement = response.get("routes");

            // If routes element is an array, there is no route data
            if (routesElement.isJsonArray()) {
                return airports;
            }

            JsonObject routesObject = routesElement.getAsJsonObject();

            // Loop through each route entry
            for (String key : routesObject.keySet()) {
                JsonObject routeObj = routesObject.getAsJsonObject(key);
                JsonObject airportObj = routeObj.getAsJsonObject("airport");

                // Parse the airport object and add it to the list
                AirportDTO airport = this.gson.fromJson(airportObj, AirportDTO.class);
                airports.add(airport);
            }
        }

        return airports;
    }

    private JsonObject getResponseObject(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        return jsonObject.getAsJsonObject("response");
    }
}
