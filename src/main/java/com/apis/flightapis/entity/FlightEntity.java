package com.apis.flightapis.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "FLIGHT")
public class FlightEntity {
    @Column(name = "Flight_Id")
    @Id
    @GeneratedValue
    private UUID flightId;
    @Column(name = "Airline_Name")
    private String airlineName;
    @Column(name = "Flight_Number")
    private String flightNumber;
    @Column(name = "Origin_Airport")
    private String originAirport;
    @Column(name = "Origin_Airport_Code")
    private String originAirportCode;
    @Column(name = "Destination_Airport")
    private String destinationAirport;
    @Column(name = "Destination_Airport_Code")
    private String destinationAirportCode;
    @Column(name = "Departure_Date_And_Time")
    private String departureDateAndTime;
    @Column(name = "Arrival_Date_And_Time")
    private String arrivalDateAndTime;
    @Column(name = "Cabins")
    private String cabins;
    @Column(name = "Cabin_Class")
    private String cabinClass;
    @Column(name = "Number_Of_Seats")
    private int numberOfSeats;

    public FlightEntity() {
    }

    public FlightEntity(String airlineName, String flightNumber, String originAirport, String originAirportCode, String destinationAirport, String destinationAirportCode, String departureDateAndTime, String arrivalDateAndTime, String cabins, String cabinClass, int numberOfSeats) {
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

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
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

    @Override
    public String toString() {
        return "FlightEntity{" +
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

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
