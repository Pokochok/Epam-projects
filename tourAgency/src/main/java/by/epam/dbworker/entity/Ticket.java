package by.epam.dbworker.entity;

import java.util.Objects;

public class Ticket {
    private int id;
    private int flightNumber;
    private int ticketNumber;
    private String departureAirport;
    private String arrivalAirport;
    private long departureDateTime;
    private long arrivalDateTime;

    public Ticket() {
    }

    public Ticket(int flightNumber, int ticketNumber, String departureAirport, String arrivalAirport, long departureDateTime, long arrivalDateTime) {
        this.id = 0;
        this.flightNumber = flightNumber;
        this.ticketNumber = ticketNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public long getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(long departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public long getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(long arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                flightNumber == ticket.flightNumber &&
                ticketNumber == ticket.ticketNumber &&
                departureDateTime == ticket.departureDateTime &&
                arrivalDateTime == ticket.arrivalDateTime &&
                Objects.equals(departureAirport, ticket.departureAirport) &&
                Objects.equals(arrivalAirport, ticket.arrivalAirport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketNumber, departureDateTime, arrivalDateTime);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flightNumber=" + flightNumber +
                ", ticketNumber=" + ticketNumber +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", departureDateTime=" + departureDateTime +
                ", arrivalDateTime=" + arrivalDateTime +
                '}';
    }
}
