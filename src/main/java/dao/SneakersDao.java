package dao;

import models.Shoes;
import models.Sneakers;

import java.util.List;

/**
 * Created by Guest on 8/23/17.
 */
public interface SneakersDao {
    //create
    void add (Sneakers sneakers);

    //read
    List<Sneakers> getAll();

    Sneakers findSneakerById(int id);

    //update
    void updateSneakers(String brand, String shoeColor, double shoeSize, double price, boolean laces, String sneakerType, int id);

    //delete
    void deleteAllSneakers(String sneakerType);

    void deleteSneakerbyId(int id);

}
