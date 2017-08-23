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
    List<Sneakers> getAll(String sneakerType);

    Sneakers findSneakerById(int id);

    //update
    void updateSneakers(String brand, String shoeColor, double size, double price, int id);

    //delete
    void deleteAllSneakers();

    void deleteSneakerbyId(int id);

}
