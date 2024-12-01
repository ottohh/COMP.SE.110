//package org.controllers;

import org.controllers.FlightController;
import org.example.FlightService.FlightDTO;
import org.example.FlightService.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FlightControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
    }

    @Test
    public void testSearchFlights() throws Exception {
        FlightDTO flight1 = new FlightDTO(1, 150.0f, "2024-12-01", "2h 30m", 200, "AA", "Boeing 737");
        FlightDTO flight2 = new FlightDTO(2, 200.0f, "2024-12-02", "3h 45m", 250, "BA", "Airbus A320");
        List<FlightDTO> flights = Arrays.asList(flight1, flight2);

        when(flightService.searchFlights("Origin1", "Destination1", "2024-12-01", "2024-12-10", 1)).thenReturn(flights);

        mockMvc.perform(get("/flights")
                .param("origin", "Origin1")
                .param("destination", "Destination1")
                .param("departureDate", "2024-12-01")
                .param("returnDate", "2024-12-10")
                .param("adults", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'totalPrice':150.0,'lastTicketingDate':'2024-12-01','duration':'2h 30m','co2Emissions':200,'carrierCode':'AA','aircraft':'Boeing 737'},{'id':2,'totalPrice':200.0,'lastTicketingDate':'2024-12-02','duration':'3h 45m','co2Emissions':250,'carrierCode':'BA','aircraft':'Airbus A320'}]"));
    }
}