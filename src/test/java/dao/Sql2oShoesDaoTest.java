package dao;

import models.Shoes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import org.sql2o.Connection;

import java.util.List;

import static org.junit.Assert.*;


public class Sql2oShoesDaoTest {
    private Sql2oShoesDao shoesDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        shoesDao = new Sql2oShoesDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add() throws Exception {
        Shoes newShoes = newShoes();
        int id = newShoes.getId();
        shoesDao.add(newShoes);
        assertNotEquals(id, newShoes.getId());
    }

    @Test
    public void getAll() throws Exception {
        Shoes newShoes = newShoes();
        Shoes otherShoes =  otherShoes();
        shoesDao.add(newShoes);
        shoesDao.add(otherShoes);
        List<Shoes> newList = shoesDao.getAll();
        assertEquals(2, newList.size());
    }

    @Test
    public void findById() throws Exception {
        Shoes newShoes = newShoes();
        Shoes otherShoes =  otherShoes();
        shoesDao.add(newShoes);
        shoesDao.add(otherShoes);
        int id = newShoes.getId();
        Shoes find = shoesDao.findById(id);
        assertEquals(newShoes.getBrand(), find.getBrand());
    }

    @Test
    public void update() throws Exception {
        Shoes newShoes = newShoes();
        shoesDao.add(newShoes);
        int id = newShoes.getId();
        shoesDao.update("test", "pink", 7, 6400, id);
        assertNotEquals("Nike", shoesDao.findById(id).getBrand());
    }

    @Test
    public void deleteAll() throws Exception {
        Shoes newShoes = newShoes();
        Shoes otherShoes =  otherShoes();
        shoesDao.add(newShoes);
        shoesDao.add(otherShoes);
        shoesDao.deleteAll();
        assertEquals(0, shoesDao.getAll().size());
    }

    @Test
    public void delete() throws Exception {
        Shoes newShoes = newShoes();
        Shoes otherShoes =  otherShoes();
        shoesDao.add(newShoes);
        shoesDao.add(otherShoes);
        int deleteId = newShoes.getId();
        shoesDao.delete(deleteId);
        assertEquals(1, shoesDao.getAll().size());
    }

    public Shoes newShoes(){
        return new Shoes("Nike", "pizza", 9.5, 100);
    }
    public Shoes otherShoes(){
        return new Shoes("Adidas", "grey", 8.5, 100);
    }
}