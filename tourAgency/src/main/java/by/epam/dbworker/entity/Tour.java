package by.epam.dbworker.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Tour {
    private int id;
    private String tourName;
    private Long departureDate;
    private Long arrivalDate;
    private String departureCity;
    private String arrivalCity;
    private String arrivalCountry;
    private String hotel;
    private String nutrition;
    private int adultsNumber;
    private int childrenNumber;
    private BigDecimal price;
    private String status;

    public Tour() {
    }

    public Tour(String tourName, Long departureDate, Long arrivalDate, String departureCity, String arrivalCity, String arrivalCountry,
                String hotel, String nutrition, int adultsNumber, int childrenNumber, BigDecimal price, String status) {
        this.id = 0;
        this.tourName = tourName;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.arrivalCountry = arrivalCountry;
        this.hotel = hotel;
        this.nutrition = nutrition;
        this.adultsNumber = adultsNumber;
        this.childrenNumber = childrenNumber;
        this.price = price;
        this.status = status;
    }


    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureDate() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy");
        return formatForDateNow.format(new Date(departureDate));
    }

    public Long getDepartureDateLong() {
        return departureDate;
    }

    public void setDepartureDate(Long departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy");
        return formatForDateNow.format(new Date(arrivalDate));
    }

    public Long getArrivalDateLong() {
        return arrivalDate;
    }

    public void setArrivalDate(Long arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalCountry() {
        return arrivalCountry;
    }

    public void setArrivalCountry(String arrivalCountry) {
        this.arrivalCountry = arrivalCountry;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public int getAdultsNumber() {
        return adultsNumber;
    }

    public void setAdultsNumber(int adultsNumber) {
        this.adultsNumber = adultsNumber;
    }

    public int getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(int childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return id == tour.id &&
                adultsNumber == tour.adultsNumber &&
                childrenNumber == tour.childrenNumber &&
                Objects.equals(tourName, tour.tourName) &&
                Objects.equals(departureDate, tour.departureDate) &&
                Objects.equals(arrivalDate, tour.arrivalDate) &&
                Objects.equals(departureCity, tour.departureCity) &&
                Objects.equals(arrivalCity, tour.arrivalCity) &&
                Objects.equals(arrivalCountry, tour.arrivalCountry) &&
                Objects.equals(hotel, tour.hotel) &&
                Objects.equals(nutrition, tour.nutrition) &&
                Objects.equals(price, tour.price) &&
                Objects.equals(status, tour.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tourName, price);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", tourName='" + tourName + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", arrivalCountry='" + arrivalCountry + '\'' +
                ", hotel='" + hotel + '\'' +
                ", nutrition='" + nutrition + '\'' +
                ", adultsNumber=" + adultsNumber +
                ", childrenNumber=" + childrenNumber +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
