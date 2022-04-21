package ru.job4j.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ads")
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    private boolean sold;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public static Ads of(String description, Boolean sold, Car car) {
        Ads ads = new Ads();
        ads.description = description;
        ads.created = new Date(System.currentTimeMillis());
        ads.car = car;
        ads.sold = sold;
        return ads;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "Ads{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", created='" + created + '\''
                + ", car=" + car
                + ", sold=" + sold
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ads ads = (Ads) o;
        return id == ads.id && sold == ads.sold && Objects.equals(description, ads.description)
                && Objects.equals(created, ads.created) && Objects.equals(car, ads.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, sold, car);
    }
}