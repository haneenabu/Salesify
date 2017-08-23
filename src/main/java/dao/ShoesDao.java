package dao;

import models.Shoes;

import java.util.List;

/**
 * Created by Guest on 8/23/17.
 */
public interface ShoesDao {
    //create
    void add (Shoes shoes);

    //read
    List<Shoes> getAll();

    Shoes findById(int id);

    //update
    void update(String brand, String shoeColor, double size, double price, int id);

    //delete
    void deleteAll();

    void delete(int id);

}
