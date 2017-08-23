package dao;

import models.Shoes;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by Guest on 8/23/17.
 */
public class Sql2oShoesDao implements ShoesDao{
    private final Sql2o sql2o;

    public Sql2oShoesDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public void add(Shoes shoes) {
        String query = "INSERT INTO shoes (brand, shoeColor, shoeSize, price) VALUES (:brand, :shoeColor, :shoeSize, :price)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(query)
                    .bind(shoes)
                    .executeUpdate()
                    .getKey();
            shoes.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public List<Shoes> getAll() {
        return null;
    }

    @Override
    public Shoes findById(int id) {
        return null;
    }

    @Override
    public Shoes update(String brand, String shoeColor, double size, double price, int id) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void delete(int id) {

    }
}
