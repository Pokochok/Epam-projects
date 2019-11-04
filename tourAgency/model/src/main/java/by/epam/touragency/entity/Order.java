package by.epam.touragency.entity;

import java.util.Objects;

public class Order {
    private int id;
    private boolean paymentState;
    private Tour tour;
    private Ticket ticket;
    private User client;
    private User agent;
    private int tourId;
    private int ticketId;
    private int clientId;
    private int agentId;

    private Order(){}

    private Order(int id, boolean paymentState, Tour tour, Ticket ticket, User client, User agent) {
        this.id = id;
        this.paymentState = paymentState;
        this.tour = tour;
        this.ticket = ticket;
        this.client = client;
        this.agent = agent;
        tourId = tour.getId();
        ticketId = ticket.getId();
        clientId = client.getId();
        agentId = agent.getId();
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

    public static class OrderBuilder {
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
            if (Role.CLIENT.equals(client.getRole())) {
                this.client = client;
            }
            return this;
        }

        public OrderBuilder setAgent(User agent) {
            if (Role.AGENT.equals(agent.getRole())) {
                this.agent = agent;
            }
            return this;
        }

        public Order build() {
            if(tour == null || ticket == null){
                return new Order();
            }else {
                return new Order(id, paymentState, tour, ticket, client, agent);
            }
        }
    }

    public boolean isPaymentState() {
        return paymentState;
    }

    public int getTourId() {
        return tourId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPaymentState(boolean paymentState) {
        this.paymentState = paymentState;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
        this.tourId = tour == null ? 0 : tour.getId();
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
        this.ticketId = ticket == null ? 0 : ticket.getId();
    }

    public void setClient(User client) {
        this.client = client;
        this.clientId = client == null ? 0 : client.getId();
    }

    public void setAgent(User agent) {
        this.agent = agent;
        this.agentId = agent == null ? 0 : agent.getId();
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                paymentState == order.paymentState &&
                tourId == order.tourId &&
                ticketId == order.ticketId;
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
