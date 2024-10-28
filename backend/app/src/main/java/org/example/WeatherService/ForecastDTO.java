package org.example.WeatherService;
import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ottop
 */
public class ForecastDTO {
    public long dt;
    public Main main;
    public Weather[] weather;
    public Clouds clouds;
    public Wind wind;
    public int visibility;
    public double pop;
    public Rain rain;
    public String dt_txt;
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
    public double temp;
    @SerializedName("feels_like")
    public double feelsLike;
    @SerializedName("temp_min")
    public double tempMin;
    @SerializedName("temp_max")
    public double tempMax;
    public int pressure;
    @SerializedName("sea_level")
    public int seaLevel;
    @SerializedName("grnd_level")
    public int groundLevel;
    public int humidity;
}

class Weather {
    public int id;
    public String main;
    public String description;
    public String icon;
}

class Clouds {
    public int all;
}

class Wind {
    public double speed;
    public int deg;
    public double gust;
}

 class Rain {
    @SerializedName("3h")
    public double rainVolume;
}