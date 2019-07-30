package by.epam.dbworker.entity;

import java.util.Objects;

public class CreditCard {
    private int id;
    private int number;
    private String owner;
    private Long validity;

    public CreditCard() {
    }

    public CreditCard(int id, int number, String owner, Long validity) {
        this.id = id;
        this.number = number;
        this.owner = owner;
        this.validity = validity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getValidity() {
        return validity;
    }

    public void setValidity(Long validity) {
        this.validity = validity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return id == that.id &&
                number == that.number &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(validity, that.validity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, owner, validity);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", number=" + number +
                ", owner='" + owner + '\'' +
                ", validity=" + validity +
                '}';
    }
}
