package dao;

import models.Sneakers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/23/17.
 */
public class Sql2oSneakersDaoTest {
    private Sql2oSneakersDao sneakersDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        sneakersDao = new Sql2oSneakersDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add() throws Exception {
        Sneakers newSneaks1 = newSneaks();
        int id = newSneaks1.getId();
        sneakersDao.add(newSneaks1);
        assertNotEquals(id, newSneaks1.getId());
    }

    @Test
    public void getAllSneakersbtType() throws Exception {
        Sneakers sneaker = newSneaks();
        Sneakers sneaker1 = newSneaks();
        sneakersDao.add(sneaker);
        sneakersDao.add(sneaker1);
        List<Sneakers> sneakerList = sneakersDao.getAll(sneaker.getSneakerType());
        assertEquals(2, sneakerList.size());
    }

    @Test
    public void findSneakerById() throws Exception {

    }

    @Test
    public void updateSneakers() throws Exception {
    }

    @Test
    public void deleteAllSneakers() throws Exception {
    }

    @Test
    public void deleteSneakerbyId() throws Exception {
    }

    public Sneakers newSneaks(){
        return new Sneakers("Nike", "Tacos", 9.5, 200.0, true,"Dunks" );
    }

}