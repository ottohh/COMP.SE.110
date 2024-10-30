import org.junit.Test;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;

import static org.junit.Assert.*;

import org.example.FlightService.FlightService;


public class FlightServiceTest {
    @Test
    public void testSearchFlights() {
        FlightService flightService = new FlightService();
        FlightOfferSearch[] results;
        try {
            results = flightService.searchFlights("NYC", "MAD", "2024-11-01", "2024-11-08", 1);
            assertNotNull(results);
            assertTrue(results.length > 0);
        } catch (ResponseException e) {
            e.printStackTrace();
        }

    }
}