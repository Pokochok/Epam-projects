package by.epam.touragency.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(
            generator="generator"
    )
    @GenericGenerator(
            name = "generator",
            strategy = "increment"
    )
    private int id;
    @Column(name = "flight_number")
    private int flightNumber;
    @Column(name = "ticket_number")
    private int ticketNumber;
    @Column(name = "departure_city")
    private String departureCity;
    @Column(name = "arrival_city")
    private String arrivalCity;
    @Column(name = "departure_datetime")
    private long departureDateTime;
    @Column(name = "arrival_datetime")
    private long arrivalDateTime;

    @OneToMany(
            mappedBy = "ticket",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Order> orders = new ArrayList<>();

    public Ticket() {
    }

    private Ticket(int id, int flightNumber, int ticketNumber, String departureCity, String arrivalCity,
                   long departureDateTime, long arrivalDateTime) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.ticketNumber = ticketNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
    }

    public int getId() {
        return id;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public String getDepartureDateTime() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        return formatForDateNow.format(new Date(departureDateTime));
    }

    public long getDepartureDateTimeLong() {
        return departureDateTime;
    }

    public String getArrivalDateTime() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        return formatForDateNow.format(new Date(arrivalDateTime));
    }

    public long getArrivalDateTimeLong() {
        return arrivalDateTime;
    }

    public static class TicketBuilder{
        private int id;
        private int flightNumber;
        private int ticketNumber;
        private String departureCity;
        private String arrivalCity;
        private long departureDateTime;
        private long arrivalDateTime;

        public TicketBuilder() {
        }

        public TicketBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public TicketBuilder setFlightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public TicketBuilder setTicketNumber(int ticketNumber) {
            this.ticketNumber = ticketNumber;
            return this;
        }

        public TicketBuilder setDepartureCity(String departureCity) {
            this.departureCity = departureCity;
            return this;
        }

        public TicketBuilder setArrivalCity(String arrivalCity) {
            this.arrivalCity = arrivalCity;
            return this;
        }

        public TicketBuilder setDepartureDateTime(long departureDateTime) {
            this.departureDateTime = departureDateTime;
            return this;
        }

        public TicketBuilder setArrivalDateTime(long arrivalDateTime) {
            this.arrivalDateTime = arrivalDateTime;
            return this;
        }

        public Ticket build(){
            return new Ticket(id, flightNumber, ticketNumber, departureCity, arrivalCity, departureDateTime, arrivalDateTime);
        }
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
                Objects.equals(departureCity, ticket.departureCity) &&
                Objects.equals(arrivalCity, ticket.arrivalCity);
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
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", departureDateTime=" + departureDateTime +
                ", arrivalDateTime=" + arrivalDateTime +
                '}';
    }
}
