package org.example.FlightService;

public class FlightDTO {
    private int id;
    private int co2Emissions;
    private Float totalPrice;
    private String lastTicketingDate;
    private String duration;
    private String carrierCode;
    private String aircraft;


    // Default constructor
    public FlightDTO() {}

    // Parameterized constructor
    public FlightDTO(int id, float totalPrice, String lastTicketingDate, String duration, int co2Emissions, String carrierCode, String aircraft) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.lastTicketingDate = lastTicketingDate;
        this.duration = duration;
        this.co2Emissions = co2Emissions;
        this.carrierCode = carrierCode;
        this.aircraft = aircraft;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getLastTicketingDate() {
        return lastTicketingDate;
    }

    public void setLastTicketingDate(String lastTicketingDate) {
        this.lastTicketingDate = lastTicketingDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(int co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

}