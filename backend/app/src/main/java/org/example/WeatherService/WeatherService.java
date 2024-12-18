
package org.example.WeatherService;

import com.google.gson.Gson;
import org.example.Utils.ApiClient;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;

/**
 *
 * @authors ottop, eeroha
 */
public class WeatherService {
    // Just for testing
    public static void main(String[] args) {

        try {

            WeatherService service = new WeatherService("44.34", "10.99");
            service.LoadForeCasts();
            var forecast = service.GetForecastForDate(LocalDate.of(2024, 10, 14));
            System.out.println(forecast.dt_txt);
            System.out.println(forecast.main.feelsLike);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private WeatherResponse WeatherResponse;
    private ApiClient apiClient;

    public static String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&appid=%s&units=metric";
    private static final String API_KEY = "6569077c084bc0d7b0a1f61836267595";
    String latitude;
    String longitude;

    public WeatherService(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.apiClient = new ApiClient();
    }

    // returns true if loading succeeds false otherwise
    public boolean LoadForeCasts() {
        try {
            String url = String.format(WEATHER_API_URL, latitude, longitude, API_KEY);
            String jsonResponse = apiClient.getJsonResponse(url);
            Gson gson = new Gson();

            this.WeatherResponse = gson.fromJson(jsonResponse, WeatherResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ForecastDTO GetForecastForDate(LocalDate date) {
        LocalTime targetTimeOfTheDay = LocalTime.of(12, 0);
        for (ForecastDTO forecast : WeatherResponse.list) {
            LocalDateTime forecastDateTime = forecast.GetDateTime();

            if (forecastDateTime.toLocalDate().equals(date)
                    && forecastDateTime.toLocalTime().equals(targetTimeOfTheDay)) {
                return forecast;
            }
        }
        return WeatherResponse.list[0];
    }
}
