package com.apis.flightapis.model;


import java.util.UUID;

public class Flight {
    private UUID flightId;
    private String airlineName;
    private String flightNumber;
    private String originAirport;
    private String originAirportCode;
    private String destinationAirport;
    private String destinationAirportCode;
    private String departureDateAndTime;
    private String arrivalDateAndTime;
    private String cabins;
    private String cabinClass;
    private int numberOfSeats;

    public Flight() {

    }

    public Flight(UUID flightId, String airlineName, String flightNumber, String originAirport, String originAirportCode,
                  String destinationAirport, String destinationAirportCode, String departureDateAndTime,
                  String arrivalDateAndTime, String cabins, String cabinClass, int numberOfSeats) {
        super();
        this.flightId = flightId;
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        this.originAirport = originAirport;
        this.originAirportCode = originAirportCode;
        this.destinationAirport = destinationAirport;
        this.destinationAirportCode = destinationAirportCode;
        this.departureDateAndTime = departureDateAndTime;
        this.arrivalDateAndTime = arrivalDateAndTime;
        this.cabins = cabins;
        this.cabinClass = cabinClass;
        this.numberOfSeats = numberOfSeats;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getOriginAirportCode() {
        return originAirportCode;
    }

    public void setOriginAirportCode(String originAirportCode) {
        this.originAirportCode = originAirportCode;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public String getDepartureDateAndTime() {
        return departureDateAndTime;
    }

    public void setDepartureDateAndTime(String departureDateAndTime) {
        this.departureDateAndTime = departureDateAndTime;
    }

    public String getArrivalDateAndTime() {
        return arrivalDateAndTime;
    }

    public void setArrivalDateAndTime(String arrivalDateAndTime) {
        this.arrivalDateAndTime = arrivalDateAndTime;
    }

    public String getCabins() {
        return cabins;
    }

    public void setCabins(String cabins) {
        this.cabins = cabins;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

}
