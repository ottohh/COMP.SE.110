package org.example.FlightService;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOfferSearch.Itinerary;
import com.amadeus.resources.FlightOfferSearch.SearchSegment;

import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private Amadeus amadeus;

    public FlightService() {
        this.amadeus =  AmadeusClient.getAmadeus();
    }

    public List<FlightDTO> searchFlights(String origin, String destination, String departureDate, String returnDate, int adults) {
        try {
            FlightOfferSearch[] flightOffers = amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", origin)
                      .and("destinationLocationCode", destination)
                      .and("departureDate", departureDate)
                      .and("returnDate", returnDate)
                      .and("adults", 1)
            );

            List<FlightDTO> flightDTOs = new ArrayList<>();
            
            for (FlightOfferSearch offer : flightOffers) {
                FlightDTO dto = new FlightDTO();

                dto.setId(Integer.parseInt(offer.getId()));
                dto.setTotalPrice(Float.parseFloat(offer.getPrice().getGrandTotal()));
                dto.setLastTicketingDate(offer.getLastTicketingDate());

                for (Itinerary itinerary : offer.getItineraries()) {
                    int totalCo2Emissions = 0;

                    
                    for (SearchSegment segment  : itinerary.getSegments()) {        
                        dto.setCarrierCode(segment.getCarrierCode());
                        dto.setAircraft(segment.getAircraft().getCode());
                        if (segment.getCo2Emissions() != null){
                            totalCo2Emissions =+ segment.getCo2Emissions()[0].getWeight();
                        }
                    }
                    dto.setDuration(itinerary.getDuration());
                    dto.setCo2Emissions(totalCo2Emissions);
                }

                flightDTOs.add(dto);
            }
            return flightDTOs;
        } catch (ResponseException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

