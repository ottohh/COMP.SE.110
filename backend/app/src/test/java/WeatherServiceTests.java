/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import okhttp3.*;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.example.WeatherService.ForecastDTO;
import org.example.WeatherService.WeatherService;

/**
 *
 * @author ottop
 */
public class WeatherServiceTests {
    
    public WeatherServiceTests() {
    }

    static String mockJSON;
    static String filePath = "weatherMock.json";
    static String mockURL;
    static MockWebServer mockWebServer;
    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
        // Path to the JSON file


        try {

            mockJSON = new String(Files.readAllBytes(Paths.get(filePath)));
 
            mockWebServer = new MockWebServer();

            mockWebServer.enqueue(new MockResponse()
                    .setBody(mockJSON)
                    .addHeader("Content-Type", "application/json"));
            
            mockWebServer.enqueue(new MockResponse().setResponseCode(404));
                   

            // Start the server
            mockWebServer.start();

            // Get the URL of the mock server
            mockURL = mockWebServer.url("/").toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
        
        // Shutdown the mock server
        mockWebServer.shutdown();
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
        
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
     @Test
    public void testWeatherDataReturnedForCorrectDay() throws IOException, Exception {
        
        WeatherService service = new WeatherService("0.0","0.0");
        WeatherService.WEATHER_API_URL=mockURL;
        service.LoadForeCasts();
  
        ForecastDTO forecast = service.GetForecastForDate(LocalDate.of(2024, 10, 13));
        

        // Assertions or further processing
        var temp =forecast.GetTempature();
        assert 99.99 == temp;

    }
    
     @Test
    public void testErrorResponseFromApi() throws IOException, Exception {
        
        WeatherService service = new WeatherService("0.0","0.0");
        WeatherService.WEATHER_API_URL=mockURL;
        
 
        
        assert false == service.LoadForeCasts();;

    }
   
}
