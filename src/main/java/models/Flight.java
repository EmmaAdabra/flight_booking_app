package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
    private int flightID;
    private int airlineID;
    private String takeOffLocation;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;

    public Flight(
            int flightID, int airlineID,
            String takeOffLocation, String destination,
            LocalDate departureDate, LocalTime departureTime
    ) {
        this.flightID = flightID;
        this.airlineID = airlineID;
        this.takeOffLocation = takeOffLocation;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getAirlineID() {
        return airlineID;
    }

    public void setAirlineID(int airlineID) {
        this.airlineID = airlineID;
    }

    public String getTakeOffLocation() {
        return takeOffLocation;
    }

    public void setTakeOffLocation(String takeOffLocation) {
        this.takeOffLocation = takeOffLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}
