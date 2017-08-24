package dao;

import models.Shoes;
import models.Hiking;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/24/17.
 */
public class Sql2oHikingDaoTest {
    private Sql2oHikingDao hikingDao;
    private Sql2oShoesDao shoesDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        hikingDao = new Sql2oHikingDao(sql2o);
        shoesDao = new Sql2oShoesDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add() throws Exception {
        Hiking newKicks1 = newHike();
        int id = newKicks1.getId();
        hikingDao.add(newKicks1);
        assertNotEquals(id, newKicks1.getId());
    }

    @Test
    public void getAllHikingbtType() throws Exception {
        Hiking hiking = newHike();
        Hiking hiking1 = newHike();
        hikingDao.add(hiking);
        hikingDao.add(hiking1);
        List<Hiking> hikingList = hikingDao.getAll(hiking.getHikingType());
        assertEquals(2, hikingList.size());
    }

    @Test
    public void findSneakerById() throws Exception {
        Hiking newKicks1 = newHike();
        hikingDao.add(newKicks1);
        int id = newKicks1.getId();
        Hiking finder = hikingDao.findHikingById(id);
        assertEquals(newKicks1.getShoeColor(), finder.getShoeColor());
    }

    @Test
    public void updateHiking() throws Exception {
        Hiking hiking = newHike();
        Hiking hiking1 = newHike();
        hikingDao.add(hiking);
        hikingDao.add(hiking1);
        int id = hiking.getId();
        hikingDao.updateHikingShoes("Keen","Ugly", 9,300,"cross country", id);
        assertEquals("Keen", shoesDao.findById(id).getBrand());

    }

    @Test
    public void deleteAllHiking() throws Exception {
        Hiking hiking1 = newHike();
        Hiking hiking2 = newHike();
        hikingDao.add(hiking1);
        hikingDao.add(hiking2);
        Shoes shoes = new Shoes("Nike", "Black", 9.5, 100);
        shoesDao.add(shoes);
        String hikingType = hiking1.getHikingType();
        hikingDao.deleteAllHikingByType(hikingType);
        assertEquals(1, shoesDao.getAll().size());
        assertEquals(0, hikingDao.getAll(hikingType).size());
    }

    @Test
    public void deleteSneakerbyId() throws Exception {
        Hiking newKicks1 = newHike();
        hikingDao.add(newKicks1);
        int id = newKicks1.getId();
        System.out.println(shoesDao.getAll().size());
        hikingDao.deleteHikingbyId(id);
        assertEquals(0, shoesDao.getAll().size());
    }

    public Hiking newHike(){
        return new Hiking("Nike", "Tacos", 9.5, 200.0,  "Approach");
    }

}