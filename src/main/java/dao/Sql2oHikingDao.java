package dao;

import models.Hiking;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by Guest on 8/24/17.
 */
public class Sql2oHikingDao implements HikingDao {
    private final Sql2o sql2o;

    public Sql2oHikingDao (Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Hiking hiking) {
        String query = "INSERT INTO shoes (brand, shoeColor, shoeSize, price, hikingType, type) VALUES (:brand, :shoeColor, :shoeSize, :price, :hikingType, :type)";
        try(Connection con = sql2o.open()){
            int id= (int)con.createQuery(query)
                    .bind(hiking)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            hiking.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Hiking> getAll() {
        String query = "SELECT * FROM shoes WHERE type = :type";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .addParameter("type", "hiking")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Hiking.class);
        }
    }

    @Override
    public Hiking findHikingById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM shoes WHERE id = :id")
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Hiking.class);
        }
    }

    @Override
    public void updateHikingShoes(String brand, String shoeColor, double shoeSize, double price, String hikingType, int id) {
        String query = "UPDATE shoes SET (brand, shoeColor, shoeSize, price, hikingType) = (:brand, :shoeColor, :shoeSize, :price, :hikingType)WHERE id = :id";
        try (Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("brand", brand)
                    .addParameter("shoeColor", shoeColor)
                    .addParameter("shoeSize", shoeSize)
                    .addParameter("price", price)
                    .addParameter("hikingType", hikingType)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAllHikingByType(String hikingType) {
        String query = "DELETE FROM shoes WHERE hikingType = :hikingType";
        try (Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("hikingType", hikingType)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteHikingbyId(int id) {
        try(Connection con = sql2o.open()){
            con.createQuery("DELETE FROM shoes WHERE id = :id")
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }
}
