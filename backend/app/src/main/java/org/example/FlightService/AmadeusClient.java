package org.example.FlightService;

import com.amadeus.Amadeus;

public class AmadeusClient {
    // private static Amadeus amadeus = Amadeus.builder(System.getenv("AMADEUS_CLIENT_ID"), System.getenv("AMADEUS_CLIENT_SECRET")).build();
    private static Amadeus amadeus = Amadeus.builder("DvF8Yv7SPMR3yAX0kPBuMnEI19YzDbOR", "bVGY2ncrGhgAwTtu").build(); // TODO: Remove. used temporarily.

    public static Amadeus getAmadeus() {
        return amadeus;
    }
}