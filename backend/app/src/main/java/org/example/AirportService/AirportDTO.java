package org.example.AirportService;

public class AirportDTO {
    private String city_name;
    private String country;
    private String country_code;
    private String IATA;
    private double latitude;
    private double longitude;
    private int no_routes;
    private String name;

    // Getters and Setters
    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String cityName) {
        this.city_name = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String countryCode) {
        this.country_code = countryCode;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getNo_routes() {
        return no_routes;
    }

    public void setNo_routes(int noRoutes) {
        this.no_routes = noRoutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{\n" +
                "cityName='" + city_name + '\n' +
                ", country='" + country + '\n' +
                ", countryCode='" + country_code + '\n' +
                ", IATA='" + IATA + '\n' +
                ", latitude=" + latitude + '\n' +
                ", longitude=" + longitude + '\n' +
                ", noRoutes=" + no_routes + '\n' +
                ", name='" + name + '\n' +
                '}';
    }
}
