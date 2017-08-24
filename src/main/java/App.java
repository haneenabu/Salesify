import com.google.gson.Gson;
import dao.Sql2oHikingDao;
import dao.Sql2oShoesDao;
import dao.Sql2oSneakersDao;
import org.sql2o.Sql2o;
import models.*;

import org.sql2o.Connection;

import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oShoesDao shoesDao;
        Sql2oHikingDao hikingDao;
        Sql2oSneakersDao sneakersDao;
        Connection con;
        Gson gson = new Gson();


        String connecter = "jdbc:h2:~/salesify.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connecter,"","");
        shoesDao = new Sql2oShoesDao(sql2o);
        hikingDao = new Sql2oHikingDao(sql2o);
        sneakersDao = new Sql2oSneakersDao(sql2o);
        con = sql2o.open();

        //CREATE Hiking Shoes
        post("/hiking/new", "application/json", (request, response) -> {
            Map
            Hiking hiking = gson.fromJson(request.body(), Hiking.class);
            hikingDao.add(hiking);
            response.status(201);
            return gson.toJson(hiking);
        });
        //READ Hiking Shoe
        get("/hiking", "application/json", (request, response) -> {
            return gson.toJson(hikingDao.getAll());
        });
        get("/hiking/:id", "application/json", (request, response) -> {
           int hikingId = Integer.parseInt(request.params("id"));
           return gson.toJson(hikingDao.findHikingById(hikingId).getShoeColor());
        });

        //CREATE Sneakers
        post("/sneakers/new", "application/json", (request, response) -> {
            Sneakers sneakers = gson.fromJson(request.body(), Sneakers.class);
            sneakersDao.add(sneakers);
            response.status(201);
            return gson.toJson(sneakers);
        });

        //READ Sneakers
        get("/sneakers", "application/json", (request, response) -> {
            return gson.toJson(sneakersDao.getAll());
        });
        get("/sneakers/:id", "application/json", (request, response) -> {
           int sneakerId = Integer.parseInt(request.params("id"));
           return gson.toJson(sneakersDao.findSneakerById(sneakerId));
        });

        //FILTER - after the after
        after((request, response) ->{
            response.type("application/json");
        });


    }
}
