package com.apis.flightapis.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class Flight {
    private UUID flightId;
    @NotNull
    @Size(min = 3, max = 50, message = "airline name must be between 1 and 50 characters")
    private String airlineName;
    @NotNull
    @Size(min = 3, max = 50, message = "flight number name must be between 1 and 50 characters")
    private String flightNumber;
    @NotNull
    @Size(min = 3, max = 50, message = "origin airport must be between 1 and 50 characters")
    private String originAirport;
    @NotNull
    @Size(min = 3, max = 50, message = "origin airport code must be between 1 and 50 characters")
    private String originAirportCode;
    @NotNull
    @Size(min = 3, max = 50, message = "destination airport must be between 1 and 50 characters")
    private String destinationAirport;
    @NotNull
    @Size(min = 3, max = 50, message = "destination airport code must be between 1 and 50 characters")
    private String destinationAirportCode;
    @NotNull
    @Size(min = 10, max = 50, message = "departure date and time must be between 1 and 50 characters")
    private String departureDateAndTime;
    @NotNull
    @Size(min = 10, max = 50, message = "arrival date and time must be between 1 and 50 characters")
    private String arrivalDateAndTime;
    @NotNull
    @Size(min = 3, max = 50, message = "cabins must be between 1 and 50 characters")
    private String cabins;
    @NotNull
    @Size(min = 3, max = 50, message = "cabin class must be between 1 and 50 characters")
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

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", airlineName='" + airlineName + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", originAirport='" + originAirport + '\'' +
                ", originAirportCode='" + originAirportCode + '\'' +
                ", destinationAirport='" + destinationAirport + '\'' +
                ", destinationAirportCode='" + destinationAirportCode + '\'' +
                ", departureDateAndTime='" + departureDateAndTime + '\'' +
                ", arrivalDateAndTime='" + arrivalDateAndTime + '\'' +
                ", cabins='" + cabins + '\'' +
                ", cabinClass='" + cabinClass + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
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
