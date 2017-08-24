package models;

/**
 * Created by Guest on 8/23/17.
 */
public class Sneakers extends Shoes {
    private boolean laces;
    private String sneakerType;

    public Sneakers(String brand, String shoeColor, double shoeSize, double price, boolean laces, String sneakerType, String type) {
        super(brand, shoeColor, shoeSize, price, type);
        this.laces = laces;
        this.sneakerType = sneakerType;
    }

    //GETTERS
    public boolean isLaces() {
        return laces;
    }
    public String getSneakerType() {
        return sneakerType;
    }

    //SETTERS
    public void setLaces(boolean laces) {
        this.laces = laces;
    }
    public void setSneakerType(String sneakerType) {
        this.sneakerType = sneakerType;
    }

    //equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Sneakers sneakers = (Sneakers) o;

        if (laces != sneakers.laces) return false;
        return sneakerType.equals(sneakers.sneakerType);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (laces ? 1 : 0);
        result = 31 * result + sneakerType.hashCode();
        return result;
    }
}
