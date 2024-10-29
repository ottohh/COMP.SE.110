package org.example.FlightService;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;

public class FlightService {
    private Amadeus amadeus;

    public FlightService() {
        this.amadeus = AmadeusClient.getAmadeus();
    }

    public FlightOfferSearch[] searchFlights(String origin, String destination, String departureDate, String returnDate, int adults) throws ResponseException {
        return amadeus.shopping.flightOffersSearch.get(
            Params.with("originLocationCode", origin)
                  .and("destinationLocationCode", destination)
                  .and("departureDate", departureDate)
                  .and("returnDate", returnDate)
                  .and("adults", adults)
        );
    }
}

