import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.example.AirportService.AirportDTO;
import org.example.AirportService.AirportJsonParser;
import org.example.AirportService.AirportService;
import org.example.Utils.ApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AirportServiceTest {

    @Mock
    private ApiClient apiClient;

    @Mock
    private AirportJsonParser airportJsonParser;

    @InjectMocks
    private AirportService airportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchAirports() throws Exception {
        String jsonResponse = "[{\"IATA\":\"JFK\",\"name\":\"John F Kennedy International Airport\"}]";
        List<AirportDTO> expectedAirports = Arrays
                .asList(new AirportDTO("New York", "USA", "US", "JFK", 40.6413, -73.7781, 200, "John F Kennedy International Airport"));

        when(apiClient.getJsonResponse(anyString())).thenReturn(jsonResponse);
        when(airportJsonParser.parseAirportResponse(jsonResponse)).thenReturn(expectedAirports);

        List<AirportDTO> actualAirports = airportService.FetchAirports();

        assertEquals(expectedAirports, actualAirports);
        verify(apiClient, times(1)).getJsonResponse(anyString());
        verify(airportJsonParser, times(1)).parseAirportResponse(jsonResponse);
    }

    @Test
    public void testFetchAirportsFromRoutes() throws IOException {
        String airportCode = "JFK";
        Integer day = 1;
        String jsonResponse = "[{\"IATA\":\"LAX\",\"name\":\"Los Angeles International Airport\"}]";
        List<AirportDTO> expectedAirports = Arrays.asList(new AirportDTO("Los Angeles", "USA", "US", "LAX", 33.9416,
                -118.4085, 100, "Los Angeles International Airport"));

        when(apiClient.getJsonResponse(anyString())).thenReturn(jsonResponse);
        when(airportJsonParser.parseRoutesResponse(jsonResponse)).thenReturn(expectedAirports);

        List<AirportDTO> actualAirports = airportService.FetchAirportsFromRoutes(airportCode, day);

        assertEquals(expectedAirports, actualAirports);
        verify(apiClient, times(1)).getJsonResponse(anyString());
        verify(airportJsonParser, times(1)).parseRoutesResponse(jsonResponse);
    }
}
