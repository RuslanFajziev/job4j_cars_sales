package ru.job4j.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;
    private String model;
    private String bodyType;
    private String gearbox;
    private String carDrive;
    private int carEngine;

    public static Car of(String brand, String model, String bodyType,
                         String gearbox, String carDrive, int carEngine) {
        Car car = new Car();
        car.brand = brand;
        car.model = model;
        car.bodyType = bodyType;
        car.gearbox = gearbox;
        car.carDrive = carDrive;
        car.carEngine = carEngine;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getCarDrive() {
        return carDrive;
    }

    public void setCarDrive(String carDrive) {
        this.carDrive = carDrive;
    }

    public int getCarEngine() {
        return carEngine;
    }

    public void setCarEngine(int carEngine) {
        this.carEngine = carEngine;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", brand='" + brand + '\''
                + ", model='" + model + '\''
                + ", bodyType='" + bodyType + '\''
                + ", gearbox='" + gearbox + '\''
                + ", carDrive='" + carDrive + '\''
                + ", carEngine=" + carEngine
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
        Car car = (Car) o;
        return id == car.id && carEngine == car.carEngine && Objects.equals(brand, car.brand)
                && Objects.equals(model, car.model) && Objects.equals(bodyType, car.bodyType)
                && Objects.equals(gearbox, car.gearbox) && Objects.equals(carDrive, car.carDrive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, bodyType, gearbox, carDrive, carEngine);
    }
}