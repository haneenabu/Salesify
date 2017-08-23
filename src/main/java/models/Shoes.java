package models;

import org.omg.CORBA.INTERNAL;

import java.time.LocalDateTime;

public class Shoes {
    private String brand;
    private String shoeColor;
    private Double shoeSize;
    private Double price;
    private int id;
    private LocalDateTime createdAt;

    public Shoes(String brand, String shoeColor, double shoeSize, double price){
        this.brand = brand;
        this.shoeColor = shoeColor;
        this.shoeSize = shoeSize;
        this.price = price;
        this.createdAt = LocalDateTime.now();
    }

    //GETTTERS
    public String getBrand() {
        return brand;
    }
    public String getShoeColor() {
        return shoeColor;
    }
    public Double getShoeSize() {
        return shoeSize;
    }
    public Double getPrice() {
        return price;
    }
    public int getId() {
        return id;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    //SETTERS
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setShoeColor(String shoeColor) {
        this.shoeColor = shoeColor;
    }
    public void setShoeSize(Double size) {
        this.shoeSize = size;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setId(int id) {
        this.id = id;
    }
    //Equals and Hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shoes shoes = (Shoes) o;

        if (Double.compare(shoes.shoeSize, shoeSize) != 0) return false;
        if (Double.compare(shoes.price, price) != 0) return false;
        if (!brand.equals(shoes.brand)) return false;
        return shoeColor.equals(shoes.shoeColor);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = brand.hashCode();
        result = 31 * result + shoeColor.hashCode();
        temp = Double.doubleToLongBits(shoeSize);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
