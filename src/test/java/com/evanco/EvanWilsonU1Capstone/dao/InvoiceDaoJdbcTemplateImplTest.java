package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.Game;
import com.evanco.EvanWilsonU1Capstone.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoJdbcTemplateImplTest {

    // Dependency injection
    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    TshirtDao tshirtDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    SalesTaxDao salesTaxDao;
    @Autowired
    ProcessingFeeDao processingFeeDao;

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
    public void addGetAllDeleteInvoicesTest() {

        // Add game to use in invoice
        Game game = new Game();
        game.setTitle("Running Around");
        game.setEsrb_rating("E");
        game.setDescription("Adventure game for anyone");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("LA Gaming");
        game.setQuantity(25);
        game = gameDao.createGame(game);

        Invoice invoice = new Invoice();
        invoice.setName("Evan");
        invoice.setStreet("12 main Dr");
        invoice.setCity("York");
        invoice.setState("SC");
        invoice.setZipcode("12345");
        invoice.setItem_type("Game");
        invoice.setItem_id(game.getGame_id());
        invoice.setUnit_price(game.getPrice());
        invoice.setQuantity(2);
        // Format int to multiply with big decimal
        invoice.setSubtotal(game.getPrice().multiply(new BigDecimal(invoice.getQuantity())));
        invoice.setTax(
                salesTaxDao.calculateTax(invoice.getState())
                        .multiply(invoice.getSubtotal())
        );
        invoice.setProcessing_fee(
                processingFeeDao
                        .processingFee(invoice.getItem_type())
        );
        invoice.setTotal(
                invoice.getSubtotal()
                        .add(invoice.getTax())
                        .add(invoice.getProcessing_fee())
        );

        // Add
        invoice = invoiceDao.addInvoice(invoice);

        // Get
        assertEquals(1, invoiceDao.getAllInvoices().size());

        // delete
        invoiceDao.deleteInvoice(invoice.getInvoice_id());
        assertEquals(0, invoiceDao.getAllInvoices().size());
    }

    @Test
    public void getTaxTest() {
        assertEquals(new BigDecimal("0.06"), salesTaxDao.calculateTax("MI"));
    }

    @Test
    public void getProcessingFeeTest() {
        assertEquals(new BigDecimal("1.98"), processingFeeDao.processingFee("T-shirt"));
    }


}
