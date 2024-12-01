import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import org.example.FlightService.FlightDTO;
import org.example.FlightService.FlightService;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class FlightServiceTest {
    @Test
    public void testSearchFlights() {
        FlightService flightService = new FlightService();
        List<FlightDTO> results;
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate returnDate = LocalDate.now().plusDays(7);

        try {
            results = flightService.searchFlights("NYC", "MAD", today.format(formatter), returnDate.format(formatter), 1);
            assertNotNull(results);
            assertTrue(results.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}