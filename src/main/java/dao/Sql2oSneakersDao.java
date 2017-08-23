package dao;

import models.Shoes;
import models.Sneakers;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by Guest on 8/23/17.
 */
public class Sql2oSneakersDao implements SneakersDao {
    private final Sql2o sql2o;

    public Sql2oSneakersDao (Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Sneakers sneakers) {
        String query = "INSERT INTO shoes (brand, shoeColor, shoeSize, price, laces, sneakerType) VALUES (:brand, :shoeColor, :shoeSize, :price, :laces, :sneakerType)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(query)
                    .bind(sneakers)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            sneakers.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Sneakers> getAll(String sneakerType) {
        String query = "SELECT * FROM shoes WHERE sneakerType = :sneakerType";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .addParameter("sneakerType", sneakerType)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sneakers.class);
        }
    }

    @Override
    public Sneakers findSneakerById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM shoes WHERE id = :id")
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Sneakers.class);
        }
    }

    @Override
    public void updateSneakers(String brand, String shoeColor, double size, double price, int id) {

    }

    @Override
    public void deleteAllSneakers(String sneakerType) {
        String query = "DELETE FROM shoes WHERE sneakerType = :sneakerType";
        try (Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("sneakerType", sneakerType)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }

    }

    @Override
    public void deleteSneakerbyId(int id) {
        try(Connection con = sql2o.open()){
             con.createQuery("DELETE FROM shoes WHERE id = :id")
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }
}
