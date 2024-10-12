package org.example.WeatherService;
import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ottop
 */
public class ForecastDTO {
    long dt;
    Main main;
    Weather[] weather;
    Clouds clouds;
    Wind wind;
    int visibility;
    double pop;
    Rain rain;
    String dt_txt;
    public LocalDateTime GetDateTime()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dt_txt, formatter);
    } 
    public double GetTempature()
    {
        return main.feelsLike;
    }
}


class Main {
    double temp;
    @SerializedName("feels_like")
    double feelsLike;
    @SerializedName("temp_min")
    double tempMin;
    @SerializedName("temp_max")
    double tempMax;
    int pressure;
    @SerializedName("sea_level")
    int seaLevel;
    @SerializedName("grnd_level")
    int groundLevel;
    int humidity;
}

class Weather {
    int id;
    String main;
    public String description;
    String icon;
}

class Clouds {
    int all;
}

class Wind {
    double speed;
    int deg;
    double gust;
}

 class Rain {
    @SerializedName("3h")
    double rainVolume;
}