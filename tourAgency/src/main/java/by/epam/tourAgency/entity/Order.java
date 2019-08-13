package by.epam.tourAgency.entity;

import java.util.Objects;

public class Order {
    private int id;
    private boolean paymentState;
    private Tour tour;
    private Ticket ticket;
    private User client;
    private User agent;

    private Order(int id, boolean paymentState, Tour tour, Ticket ticket, User client, User agent) {
        this.id = id;
        this.paymentState = paymentState;
        this.tour = tour;
        this.ticket = ticket;
        this.client = client;
        this.agent = agent;
    }

    public int getId() {
        return id;
    }

    public boolean getPaymentState() {
        return paymentState;
    }

    public Tour getTour() {
        return tour;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public User getClient() {
        return client;
    }

    public User getAgent() {
        return agent;
    }

    public static class OrderBuilder{
        private int id;
        private boolean paymentState;
        private Tour tour;
        private Ticket ticket;
        private User client;
        private User agent;

        public OrderBuilder() {
        }

        public OrderBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setPaymentState(boolean paymentState) {
            this.paymentState = paymentState;
            return this;
        }

        public OrderBuilder setTour(Tour tour) {
            this.tour = tour;
            return this;
        }

        public OrderBuilder setTicket(Ticket ticket) {
            this.ticket = ticket;
            return this;
        }

        public OrderBuilder setClient(User client) {
            if(Role.CLIENT.equals(client.getRole())) {
                this.client = client;
            }
            return this;
        }

        public OrderBuilder setAgent(User agent) {
            if (Role.AGENT.equals(agent.getRole()))
                this.agent = agent;
            return this;
        }

        public Order build(){
            return new Order(id, paymentState, tour, ticket, client, agent);
        }
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
                ", client=" + client +
                ", agent=" + agent +
                '}';
    }
}
