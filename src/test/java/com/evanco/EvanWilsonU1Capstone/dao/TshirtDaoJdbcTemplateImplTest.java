package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.Tshirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TshirtDaoJdbcTemplateImplTest {

    // Dependency injection
    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    TshirtDao tshirtDao;
    @Autowired
    InvoiceDao invoiceDao;

    // Clear relevant tables before testing
    @Before
    public void setUp() throws Exception {
        // Clear tables in order before tests
        invoiceDao.getAllInvoices()
                .stream()
                .forEach(i -> invoiceDao.deleteInvoice(i.getInvoice_id()));
        consoleDao.getAllConsoles()
                .stream()
                .forEach(c -> consoleDao.deleteConsole(c.getConsole_id()));
        gameDao.getAllGames()
                .stream()
                .forEach(g -> gameDao.deleteGame(g.getGame_id()));
        tshirtDao.getAllTshirts()
                .stream()
                .forEach(t -> tshirtDao.deleteTshirt(t.getT_shirt_id()));
    }

    @Test
    public void addGetDeleteGetAllTshirtsTest() {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("XL");
        tshirt.setColor("Yellow");
        tshirt.setDescription("Vintage gamers' gear");
        tshirt.setPrice(new BigDecimal("24.99"));
        tshirt.setQuantity(30);

        // Add
        tshirt = tshirtDao.createTshirt(tshirt);

        // Get
        Tshirt tshirt2 = tshirtDao.getTshirtById(tshirt.getT_shirt_id());
        assertEquals(tshirt, tshirt2);

        // Delete
        tshirtDao.deleteTshirt(tshirt.getT_shirt_id());
        assertNull(tshirtDao.getTshirtById(tshirt.getT_shirt_id()));

        // Get All
        tshirtDao.createTshirt(tshirt);
        assertEquals(1, tshirtDao.getAllTshirts().size());
    }

    @Test
    public void updateTshirtTest(){
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("XL");
        tshirt.setColor("Yellow");
        tshirt.setDescription("Vintage gamers' gear");
        tshirt.setPrice(new BigDecimal("24.99"));
        tshirt.setQuantity(30);

        // Add
        tshirt = tshirtDao.createTshirt(tshirt);

        // Update
        tshirt.setColor("UPDATE");
        tshirtDao.updateTshirt(tshirt);

        //Get
        Tshirt tshirt2 = tshirtDao.getTshirtById(tshirt.getT_shirt_id());
        assertEquals(tshirt, tshirt2);
    }

    @Test
    public void findTshirtsByColorTest(){
        // Add 2 shirts with color "White"
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("XL");
        tshirt.setColor("White");
        tshirt.setDescription("Vintage gamers' gear");
        tshirt.setPrice(new BigDecimal("24.99"));
        tshirt.setQuantity(30);
        tshirt = tshirtDao.createTshirt(tshirt);
        tshirt = tshirtDao.createTshirt(tshirt);

        // Add 3 shirts with color "Black"
        tshirt = new Tshirt();
        tshirt.setSize("XL");
        tshirt.setColor("Black");
        tshirt.setDescription("Vintage gamers' gear");
        tshirt.setPrice(new BigDecimal("24.99"));
        tshirt.setQuantity(30);
        tshirt = tshirtDao.createTshirt(tshirt);
        tshirt = tshirtDao.createTshirt(tshirt);
        tshirt = tshirtDao.createTshirt(tshirt);

        // Get all t-shirts
        assertEquals(5, tshirtDao.getAllTshirts().size());

        // Get all t- shirts by color
        assertEquals(2, tshirtDao.findTshirtsByColor("White").size());
    }

    @Test
    public void findTshirtsBySizeTest(){
        // Add 2 shirts with Size "S"
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("S");
        tshirt.setColor("White");
        tshirt.setDescription("Vintage gamers' gear");
        tshirt.setPrice(new BigDecimal("24.99"));
        tshirt.setQuantity(30);
        tshirt = tshirtDao.createTshirt(tshirt);
        tshirt = tshirtDao.createTshirt(tshirt);

        // Add 3 shirts with color "XL"
        tshirt = new Tshirt();
        tshirt.setSize("XL");
        tshirt.setColor("Black");
        tshirt.setDescription("Vintage gamers' gear");
        tshirt.setPrice(new BigDecimal("24.99"));
        tshirt.setQuantity(30);
        tshirt = tshirtDao.createTshirt(tshirt);
        tshirt = tshirtDao.createTshirt(tshirt);
        tshirt = tshirtDao.createTshirt(tshirt);

        // Get all t-shirts
        assertEquals(5, tshirtDao.getAllTshirts().size());

        // Get all t- shirts by color
        assertEquals(2, tshirtDao.findTshirtsBySize("S").size());


    }
}
