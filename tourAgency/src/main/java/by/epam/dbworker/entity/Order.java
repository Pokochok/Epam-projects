package by.epam.dbworker.entity;

import java.util.Objects;

public class Order {
    private int id;
    private boolean paymentState;
    private Tour tour;
    private Ticket ticket;
    private User user;

    public Order() {
    }

    public Order(boolean paymentState, Tour tour, Ticket ticket) {
        this.id = 0;
        this.paymentState = paymentState;
        this.tour = tour;
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPaymentState() {
        return paymentState;
    }

    public void setPaymentState(boolean paymentState) {
        this.paymentState = paymentState;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                paymentState == order.paymentState &&
                Objects.equals(tour, order.tour) &&
                Objects.equals(ticket, order.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticket);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", paymentState=" + paymentState +
                ", tour=" + tour +
                ", ticket=" + ticket +
                '}';
    }
}
