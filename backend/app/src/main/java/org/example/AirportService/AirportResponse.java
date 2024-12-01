package org.example.AirportService;

import java.util.ArrayList;
import java.util.List;

public class AirportResponse {
    private List<AirportDTO> airports;

    public List<AirportDTO> getAirportsWhichHaveRoutes() {
        List<AirportDTO> result = new ArrayList<AirportDTO>();
        for (AirportDTO airportDTO : airports) {
            if (airportDTO.getNo_routes() > 0) {
                result.add(airportDTO);
            }
        }
        return result;
    }

    public void setAirports(List<AirportDTO> airports) {
        this.airports = airports;
    }
}
