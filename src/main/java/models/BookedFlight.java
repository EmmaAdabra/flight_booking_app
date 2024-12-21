package models;

import java.time.LocalDate;

public class BookedFlight {
    private int bookedID;
    private int customerID;
    private int flightID;
    private LocalDate bookedDate;

    public BookedFlight(int bookedID, int customerID, int flightID, LocalDate bookedDate) {
        this.bookedID = bookedID;
        this.customerID = customerID;
        this.flightID = flightID;
        this.bookedDate = bookedDate;
    }

    public int getBookedID() {
        return bookedID;
    }

    public void setBookedID(int bookedID) {
        this.bookedID = bookedID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public LocalDate getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(LocalDate bookedDate) {
        this.bookedDate = bookedDate;
    }
}
