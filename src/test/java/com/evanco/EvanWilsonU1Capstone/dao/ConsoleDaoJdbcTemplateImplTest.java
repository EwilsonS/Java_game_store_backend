package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleDaoJdbcTemplateImplTest {

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
    public void addGetDeleteGetAllConsoleTest() {
        Console console = new Console();
        console.setModel("model");
        console.setManufacturer("Manf.");
        console.setMemory_amount("64bit");
        console.setProcessor("x243");
        console.setPrice(new BigDecimal("699.85"));
        console.setQuantity(8);

        // Add
        console = consoleDao.createConsole(console);

        // Get
        Console console2 = consoleDao.getConsoleById(console.getConsole_id());
        assertEquals(console, console2);

        // Delete
        consoleDao.deleteConsole(console.getConsole_id());
        assertNull(consoleDao.getConsoleById(console.getConsole_id()));

        // Get All
        consoleDao.createConsole(console);
        assertEquals(1, consoleDao.getAllConsoles().size());
        System.out.println("EVAN LOVES YOU!!!!!!!!!! YES, YOU!");


    }

    @Test
    public void updateConsoleTest() {
        Console console = new Console();
        console.setModel("model");
        console.setManufacturer("Manf.");
        console.setMemory_amount("64bit");
        console.setProcessor("x243");
        console.setPrice(new BigDecimal("699.85"));
        console.setQuantity(8);

        // Add
        console = consoleDao.createConsole(console);

        //update
        console.setModel("UPDATE");
        consoleDao.updateConsole(console);
        // Get
        Console console2 = consoleDao.getConsoleById(console.getConsole_id());
        assertEquals(console, console2);

    }

    @Test
    public void findConsoleByManufacturerTest() {
        // Add 2 consoles with mfr "ONE"
        Console console = new Console();
        console.setModel("model");
        console.setManufacturer("ONE");
        console.setMemory_amount("64bit");
        console.setProcessor("x243");
        console.setPrice(new BigDecimal("699.85"));
        console.setQuantity(8);
        consoleDao.createConsole(console);
        consoleDao.createConsole(console);

        // Add 3 consoles with mfr "TWO"
        console = new Console();
        console.setModel("model");
        console.setManufacturer("TWO");
        console.setMemory_amount("64bit");
        console.setProcessor("x243");
        console.setPrice(new BigDecimal("699.85"));
        console.setQuantity(8);
        consoleDao.createConsole(console);
        consoleDao.createConsole(console);
        consoleDao.createConsole(console);

        // Get all consoles
        assertEquals(5, consoleDao.getAllConsoles().size());

        // Get consoles by mfr
        assertEquals(2, consoleDao.findConsolesByManufacturer("ONE").size());

    }

}
