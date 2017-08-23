package dao;

import models.Shoes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import org.sql2o.Connection;

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
    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void deleteAll() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    public Shoes newShoes(){
        return new Shoes("Nike", "pizza", 9, 100);
    }
}