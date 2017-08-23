package dao;

import models.Shoes;
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
    private Sql2oShoesDao shoesDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        sneakersDao = new Sql2oSneakersDao(sql2o);
        shoesDao = new Sql2oShoesDao(sql2o);
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
        Sneakers sneaker1 = newSneaks();
        sneakersDao.add(sneaker1);
        int id = sneaker1.getId();
        Sneakers finder = sneakersDao.findSneakerById(id);
        assertEquals(sneaker1.getShoeColor(), finder.getShoeColor());
    }

    @Test
    public void updateSneakers() throws Exception {

    }

    @Test
    public void deleteAllSneakers() throws Exception {
        Sneakers sneaker1 = newSneaks();
        Sneakers sneaker2 = newSneaks();
        sneakersDao.add(sneaker1);
        sneakersDao.add(sneaker2);
        Shoes shoes = new Shoes("Nike", "Black", 9.5, 100);
        shoesDao.add(shoes);
        String sneakertype = sneaker1.getSneakerType();
        sneakersDao.deleteAllSneakers(sneakertype);
        assertEquals(1, shoesDao.getAll().size());
        assertEquals(0, sneakersDao.getAll(sneakertype).size());
    }

    @Test
    public void deleteSneakerbyId() throws Exception {
        Sneakers sneaker1 = newSneaks();
        sneakersDao.add(sneaker1);
        int id = sneaker1.getId();
        System.out.println(shoesDao.getAll().size());
        sneakersDao.deleteSneakerbyId(id);
        assertEquals(0, shoesDao.getAll().size());
    }

    public Sneakers newSneaks(){
        return new Sneakers("Nike", "Tacos", 9.5, 200.0, true,"Dunks" );
    }

}