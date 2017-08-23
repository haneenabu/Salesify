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
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            shoes.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Shoes> getAll() {
        String query = "SELECT * FROM shoes";
        try (Connection con = sql2o.open()) {
            return con.createQuery(query)
                    .executeAndFetch(Shoes.class);
        }
    }

    @Override
    public Shoes findById(int id) {
        String query = "SELECT * FROM shoes WHERE id = :id";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Shoes.class);
        }
    }

    @Override
    public void update(String brand, String shoeColor, double shoeSize, double price, int id) {
        String query = "UPDATE shoes SET (brand, shoeColor, shoeSize, price) = (:brand, :shoeColor, :shoeSize, :price)";
        try (Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("brand", brand)
                    .addParameter("shoeColor", shoeColor)
                    .addParameter("shoeSize", shoeSize)
                    .addParameter("price", price)
                    .executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAll() {
        try(Connection con = sql2o.open()){
            con.createQuery("DELETE FROM shoes")
                    .executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection con = sql2o.open()){
            con.createQuery("DELETE FROM shoes WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
