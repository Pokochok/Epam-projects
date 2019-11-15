package by.epam.touragency.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
@Entity
@Component
@Table(name = "tours", uniqueConstraints = {
        @UniqueConstraint(columnNames = "tour_name")})
public class Tour {
    @Id
    @GeneratedValue(
            generator="generator"
    )
    @GenericGenerator(
            name = "generator",
            strategy = "increment"
    )
    private int id;
    @Column(name = "tour_name")
    private String tourName;
    @Column(name = "departure_date")
    private Long departureDate;
    @Column(name = "arrival_date")
    private Long arrivalDate;
    @Column(name = "departure_city")
    private String departureCity;
    @Column(name = "arrival_city")
    private String arrivalCity;
    @Column(name = "arrival_country")
    private String arrivalCountry;
    private String hotel;
    private String nutrition;
    @Column(name = "adults_number")
    private int adultsNumber;
    @Column(name = "children_number")
    private int childrenNumber;
    private BigDecimal price;
    private String status;

    public Tour() {
    }

    public Tour(int id, String tourName, Long departureDate, Long arrivalDate, String departureCity, String arrivalCity, String arrivalCountry,
                 String hotel, String nutrition, int adultsNumber, int childrenNumber, BigDecimal price, String status) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String departureDateString() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        return formatForDateNow.format(new Date(departureDate));
    }

    public Long getDepartureDate() {
        return departureDate;
    }

    public String arrivalDateString() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        return formatForDateNow.format(new Date(arrivalDate));
    }

    public Long getArrivalDate() {
        return arrivalDate;
    }

    public String getDepartureCity() {
        return departureCity;
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

    public String getHotel() {
        return hotel;
    }

    public String getNutrition() {
        return nutrition;
    }

    public int getAdultsNumber() {
        return adultsNumber;
    }

    public int getChildrenNumber() {
        return childrenNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public void setDepartureDate(Long departureDate) {
        this.departureDate = departureDate;
    }

    public void setArrivalDate(Long arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalCountry(String arrivalCountry) {
        this.arrivalCountry = arrivalCountry;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public void setAdultsNumber(int adultsNumber) {
        this.adultsNumber = adultsNumber;
    }

    public void setChildrenNumber(int childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class TourBuilder{
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

        public TourBuilder() {
        }

        public TourBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public TourBuilder setTourName(String tourName) {
            this.tourName = tourName;
            return this;
        }

        public TourBuilder setDepartureDate(Long departureDate) {
            this.departureDate = departureDate;
            return this;
        }

        public TourBuilder setArrivalDate(Long arrivalDate) {
            this.arrivalDate = arrivalDate;
            return this;
        }

        public TourBuilder setDepartureCity(String departureCity) {
            this.departureCity = departureCity;
            return this;
        }

        public TourBuilder setArrivalCity(String arrivalCity) {
            this.arrivalCity = arrivalCity;
            return this;
        }

        public TourBuilder setArrivalCountry(String arrivalCountry) {
            this.arrivalCountry = arrivalCountry;
            return this;
        }

        public TourBuilder setHotel(String hotel) {
            this.hotel = hotel;
            return this;
        }

        public TourBuilder setNutrition(String nutrition) {
            this.nutrition = nutrition;
            return this;
        }

        public TourBuilder setAdultsNumber(int adultsNumber) {
            this.adultsNumber = adultsNumber;
            return this;
        }

        public TourBuilder setChildrenNumber(int childrenNumber) {
            this.childrenNumber = childrenNumber;
            return this;
        }

        public TourBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public TourBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Tour build(){
            return new Tour(id, tourName, departureDate, arrivalDate, departureCity, arrivalCity, arrivalCountry, hotel,
                    nutrition, adultsNumber, childrenNumber, price, status);
        }
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
