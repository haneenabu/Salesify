package dao;

import models.Hiking;

import java.util.List;

public interface HikingDao {
    //create
    void add (Hiking hiking);

    //read
    List<Hiking> getAll();

    Hiking findHikingById(int id);

    //update
    void updateHikingShoes(String brand, String shoeColor, double shoeSize, double price, String hikingType, int id);

    //delete
    void deleteAllHikingByType(String sneakerType);

    void deleteHikingbyId(int id);

}
