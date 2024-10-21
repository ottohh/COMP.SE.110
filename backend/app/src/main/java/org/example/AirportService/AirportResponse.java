package org.example.AirportService;

import java.util.List;

public class AirportResponse {
    private List<AirportDTO> airports;

    public List<AirportDTO> getAirports() {
        return airports;
    }

    public void setAirports(List<AirportDTO> airports) {
        this.airports = airports;
    }
}
