package models;


public class Hiking extends Shoes {
    private String hikingType;

    public Hiking(String brand, String shoeColor, Float size, Float price, String hikingType) {
        super(brand, shoeColor, size, price);
        this.hikingType = hikingType;
    }
    //GETTER
    public String getHikingType() {
        return hikingType;
    }

    //SETTER
    public void setHikingType(String hikingType) {
        this.hikingType = hikingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Hiking hiking = (Hiking) o;

        return hikingType.equals(hiking.hikingType);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + hikingType.hashCode();
        return result;
    }
}
