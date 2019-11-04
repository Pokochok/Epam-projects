package by.epam.touragency.entity;

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

    private Tour(int id, String tourName, Long departureDate, Long arrivalDate, String departureCity, String arrivalCity, String arrivalCountry,
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

    public String getDepartureDate() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        return formatForDateNow.format(new Date(departureDate));
    }

    public Long getDepartureDateLong() {
        return departureDate;
    }

    public String getArrivalDate() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        return formatForDateNow.format(new Date(arrivalDate));
    }

    public Long getArrivalDateLong() {
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
