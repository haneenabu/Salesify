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
    private String type;

    public Shoes(String brand, String shoeColor, double shoeSize, double price, String type){
        this.brand = brand;
        this.shoeColor = shoeColor;
        this.shoeSize = shoeSize;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        this.type = type;
    }

    //GETTTERS
    public String getBrand() {
        return brand;
    }
    public String getType(){return type; }
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
    public void setType(String type) {this.type = type; }
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

        if (!brand.equals(shoes.brand)) return false;
        if (!shoeColor.equals(shoes.shoeColor)) return false;
        if (!shoeSize.equals(shoes.shoeSize)) return false;
        if (!price.equals(shoes.price)) return false;
        return type.equals(shoes.type);
    }

    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + shoeColor.hashCode();
        result = 31 * result + shoeSize.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
