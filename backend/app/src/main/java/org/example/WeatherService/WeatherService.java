
package org.example.WeatherService;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.LocalDate;

/**
 *
 * @author ottop
 */
public class WeatherService 
{
    // Just for testing
    public static void main(String[] args) {
       
        try {
            
        WeatherService service = new WeatherService("44.34","10.99");
        service.LoadForeCasts();
        var forecast = service.GetForecastForDate(LocalDate.of(2024, 10, 14));
        System.out.println(forecast.dt_txt);
        System.out.println(forecast.main.feelsLike);
        
        
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private WeatherResponse WeatherResponse;
    public static String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&appid=%s&units=metric";
    private static final String API_KEY = "";
    String latitude;
    String longitude;
    
    public WeatherService(String latitude,String longitude)
    {
        this.latitude=latitude;
        this.longitude=longitude;
    }
    
    //returns true if loading succeeds false otherwise
    public boolean LoadForeCasts()
    {
        try {
        String url = String.format(WEATHER_API_URL,latitude,longitude,API_KEY);
        String jsonResponse = getJsonResponse(url);
        Gson gson = new Gson();

        this.WeatherResponse = gson.fromJson(jsonResponse, WeatherResponse.class);
        
        
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public ForecastDTO GetForecastForDate(LocalDate date)
    {
        LocalTime targetTimeOfTheDay = LocalTime.of(12, 0);
        for (ForecastDTO forecast : WeatherResponse.list) {
            LocalDateTime forecastDateTime = forecast.GetDateTime();
            
            if (forecastDateTime.toLocalDate().equals(date) && forecastDateTime.toLocalTime().equals(targetTimeOfTheDay)) {
                return forecast;
            }
        }
        return WeatherResponse.list[0];
    }
        
    private String getJsonResponse(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);

            return response.body().string();
        }
    }
}
