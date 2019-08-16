package com.evanco.EvanWilsonU1Capstone.service;

import com.evanco.EvanWilsonU1Capstone.dao.*;
import com.evanco.EvanWilsonU1Capstone.model.*;
import com.evanco.EvanWilsonU1Capstone.viewmodel.ConsoleViewModel;
import com.evanco.EvanWilsonU1Capstone.viewmodel.GameViewModel;
import com.evanco.EvanWilsonU1Capstone.viewmodel.OrderViewModel;
import com.evanco.EvanWilsonU1Capstone.viewmodel.TshirtViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {

    // Field injection
    InvoiceDao invoiceDao;
    SalesTaxDao salesTaxDao;
    ProcessingFeeDao processingFeeDao;
    GameDao gameDao;
    ConsoleDao consoleDao;
    TshirtDao tshirtDao;
    ManageInventoryService manageInventoryService;
    OrderService orderService;

    @Before
    public void setUp() throws Exception {

        // Run set up methods to emulate database
        setUpConsoleDaoMock();
        setUpGameDaoMock();
        setUpInvoiceDaoMock();
        setUpTshirtDaoMock();
        setUpSalesTaxDaoMock();
        setUpProcessingFeeDaoMock();

        // Connect service layer to DAOs making service layer the entry point
        orderService = new OrderService(invoiceDao, salesTaxDao, processingFeeDao, gameDao, consoleDao, tshirtDao);
        manageInventoryService = new ManageInventoryService(gameDao, consoleDao, tshirtDao);

    }

    // -----------------------------------------------------------------
    // Mock set up
    // -----------------------------------------------------------------
    private void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        Invoice invoice = new Invoice();
        invoice.setInvoice_id(1);
        invoice.setName("Evan");
        invoice.setStreet("123 Main st");
        invoice.setCity("Charlotte");
        invoice.setState("NC");
        invoice.setZipcode("29202");
        invoice.setItem_type("Game");
        invoice.setItem_id(1);
        invoice.setUnit_price(new BigDecimal("49.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("99.98"));
        invoice.setTax(new BigDecimal(".05"));
        invoice.setProcessing_fee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("106.47"));

        Invoice invoice2 = new Invoice();
        invoice2.setName("Evan");
        invoice2.setStreet("123 Main st");
        invoice2.setCity("Charlotte");
        invoice2.setState("NC");
        invoice2.setZipcode("29202");
        invoice2.setItem_type("Game");
        invoice2.setItem_id(1);
        invoice2.setUnit_price(new BigDecimal("49.99"));
        invoice2.setQuantity(2);
        invoice2.setSubtotal(new BigDecimal("99.98"));
        invoice2.setTax(new BigDecimal(".05"));
        invoice2.setProcessing_fee(new BigDecimal("1.49"));
        invoice2.setTotal(new BigDecimal("106.47"));

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice2);
        doReturn(invoices).when(invoiceDao).getAllInvoices();
    }

    private void setUpSalesTaxDaoMock() {
        salesTaxDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NC");
        salesTaxRate.setRate(new BigDecimal("0.05"));

        List<SalesTaxRate> salesTaxRates = new ArrayList<>();
        salesTaxRates.add(salesTaxRate);

        doReturn(salesTaxRates).when(salesTaxDao).getAllStates();
        doReturn(salesTaxRate.getRate()).when(salesTaxDao).calculateTax("NC");
    }

    private void setUpProcessingFeeDaoMock() {
        processingFeeDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProduct_type("Game");
        processingFee.setFee(new BigDecimal("1.49"));

        doReturn(processingFee.getFee()).when(processingFeeDao).processingFee("Game");

    }

    private void setUpGameDaoMock() {
        gameDao = mock(GameDaoJdbcTemplateImpl.class);

        Game game = new Game();
        game.setGame_id(1);
        game.setTitle("Running Around");
        game.setEsrb_rating("E");
        game.setDescription("Adventure game for anyone");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("LA Gaming");
        game.setQuantity(25);

        Game game2 = new Game();
        game2.setTitle("Running Around");
        game2.setEsrb_rating("E");
        game2.setDescription("Adventure game for anyone");
        game2.setPrice(new BigDecimal("49.99"));
        game2.setStudio("LA Gaming");
        game2.setQuantity(25);

        List<Game> games = new ArrayList<>();
        games.add(game);

        doReturn(game).when(gameDao).createGame(game2);
        doReturn(game).when(gameDao).getGameById(1);
        doReturn(games).when(gameDao).getAllGames();
        doReturn(games).when(gameDao).findGamesByRating("E");
        doReturn(games).when(gameDao).findGamesByStudio("LA Gaming");
        doReturn(games).when(gameDao).findGamesByTitle("Running Around");
    }

    private void setUpConsoleDaoMock() {
        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        Console console = new Console();
        console.setConsole_id(1);
        console.setModel("model");
        console.setManufacturer("ONE");
        console.setMemory_amount("64bit");
        console.setProcessor("x243");
        console.setPrice(new BigDecimal("699.85"));
        console.setQuantity(8);

        Console console2 = new Console();
        console2.setModel("model");
        console2.setManufacturer("ONE");
        console2.setMemory_amount("64bit");
        console2.setProcessor("x243");
        console2.setPrice(new BigDecimal("699.85"));
        console2.setQuantity(8);

        List<Console> consoles = new ArrayList<>();
        consoles.add(console);

        doReturn(console).when(consoleDao).createConsole(console2);
        doReturn(console).when(consoleDao).getConsoleById(1);
        doReturn(consoles).when(consoleDao).getAllConsoles();
        doReturn(consoles).when(consoleDao).findConsolesByManufacturer("ONE");
    }

    private void setUpTshirtDaoMock() {
        tshirtDao = mock(TshirtDaoJdbcTemplateImpl.class);

        Tshirt tshirt = new Tshirt();
        tshirt.setT_shirt_id(1);
        tshirt.setSize("XL");
        tshirt.setColor("Yellow");
        tshirt.setDescription("Vintage gamers' gear");
        tshirt.setPrice(new BigDecimal("24.99"));
        tshirt.setQuantity(30);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setSize("XL");
        tshirt2.setColor("Yellow");
        tshirt2.setDescription("Vintage gamers' gear");
        tshirt2.setPrice(new BigDecimal("24.99"));
        tshirt2.setQuantity(30);

        List<Tshirt> tshirts = new ArrayList<>();
        tshirts.add(tshirt);

        doReturn(tshirt).when(tshirtDao).createTshirt(tshirt2);
        doReturn(tshirt).when(tshirtDao).getTshirtById(1);
        doReturn(tshirts).when(tshirtDao).getAllTshirts();
        doReturn(tshirts).when(tshirtDao).findTshirtsByColor("Yellow");
        doReturn(tshirts).when(tshirtDao).findTshirtsBySize("XL");

    }

    // -----------------------------------------------------------------
    // Tests
    // -----------------------------------------------------------------

    // Order test
    @Test
    public void saveAndGetInvoicesTest() {
        OrderViewModel ovm = new OrderViewModel();
        ovm.setName("Evan");
        ovm.setStreet("123 Main st");
        ovm.setCity("Charlotte");
        ovm.setState("NC");
        ovm.setZipcode("29202");
        ovm.setItem_type("Game");
        ovm.setItem_id(1);
        ovm.setQuantity(2);
        // Save invoice
        orderService.saveOrder(ovm);

        // Get all invoices
        List<Invoice> invoices = invoiceDao.getAllInvoices();
        assertEquals(1, invoices.size());
    }

    // Game tests
    @Test
    public void saveFindFindAllGamesTest() {

        GameViewModel gvm = new GameViewModel();
        gvm.setTitle("Running Around");
        gvm.setEsrb_rating("E");
        gvm.setDescription("Adventure game for anyone");
        gvm.setPrice(new BigDecimal("49.99"));
        gvm.setStudio("LA Gaming");
        gvm.setQuantity(25);

        // save game
        gvm = manageInventoryService.saveGame(gvm);

        // Get
        GameViewModel gvm2 = manageInventoryService.findGameById(gvm.getGame_id());
        assertEquals(gvm, gvm2);

        // Get all games
        assertEquals(1, manageInventoryService.findAllGames().size());
    }

    @Test
    public void findGamesByTitleRatingStudioTest() {

        GameViewModel gvm = new GameViewModel();
        gvm.setTitle("Running Around");
        gvm.setEsrb_rating("E");
        gvm.setDescription("Adventure game for anyone");
        gvm.setPrice(new BigDecimal("49.99"));
        gvm.setStudio("LA Gaming");
        gvm.setQuantity(25);
        gvm = manageInventoryService.saveGame(gvm);

        List<GameViewModel> gameViewModelsByTitle = manageInventoryService.findGamesByTitle("Running Around");
        assertEquals("Running Around", gameViewModelsByTitle.get(0).getTitle());

        List<GameViewModel> gameViewModelsByRating = manageInventoryService.findGamesByTitle("E");
        assertEquals("E", gameViewModelsByTitle.get(0).getEsrb_rating());

        List<GameViewModel> gameViewModelsByStudio = manageInventoryService.findGamesByTitle("LA Gaming");
        assertEquals("LA Gaming", gameViewModelsByTitle.get(0).getStudio());

    }

    // Console tests
    @Test
    public void saveFindFindAllConsolesTests() {
        ConsoleViewModel cvm = new ConsoleViewModel();
        cvm.setModel("model");
        cvm.setManufacturer("ONE");
        cvm.setMemory_amount("64bit");
        cvm.setProcessor("x243");
        cvm.setPrice(new BigDecimal("699.85"));
        cvm.setQuantity(8);

        // Add
        cvm = manageInventoryService.saveConsole(cvm);

        // Get
        ConsoleViewModel cvm2 = manageInventoryService.findConsoleById(cvm.getConsole_id());
        assertEquals(cvm, cvm2);

        // Get all consoles
        assertEquals(1, manageInventoryService.findAllGames().size());
    }

    @Test
    public void findConsolesByMfrTest() {

        ConsoleViewModel cvm = new ConsoleViewModel();
        cvm.setModel("model");
        cvm.setManufacturer("ONE");
        cvm.setMemory_amount("64bit");
        cvm.setProcessor("x243");
        cvm.setPrice(new BigDecimal("699.85"));
        cvm.setQuantity(8);
        cvm = manageInventoryService.saveConsole(cvm);

        List<ConsoleViewModel> consolesByMfr = manageInventoryService.findConsolesByMfr("ONE");
        assertEquals("ONE", consolesByMfr.get(0).getManufacturer());
    }

    // Tshirt tests
    @Test
    public void saveFindFindAllTshirtTest() {
        TshirtViewModel tvm = new TshirtViewModel();
        tvm.setSize("XL");
        tvm.setColor("Yellow");
        tvm.setDescription("Vintage gamers' gear");
        tvm.setPrice(new BigDecimal("24.99"));
        tvm.setQuantity(30);

        // Add shirt
        tvm = manageInventoryService.saveTshirt(tvm);

        // Get shirt
        TshirtViewModel tvm2 = manageInventoryService.findTshirtById(tvm.getT_shirt_id());
        assertEquals(tvm, tvm2);

        // Get all shirts
        assertEquals(1, manageInventoryService.findAllTshirts().size());
    }

    @Test
    public void findTshirtsBysizeAndCololr(){
        TshirtViewModel tvm = new TshirtViewModel();
        tvm.setSize("XL");
        tvm.setColor("Yellow");
        tvm.setDescription("Vintage gamers' gear");
        tvm.setPrice(new BigDecimal("24.99"));
        tvm.setQuantity(30);
        tvm = manageInventoryService.saveTshirt(tvm);

        List<TshirtViewModel> tshirtViewModels = manageInventoryService.findTshirtsByColor("Yellow");
        assertEquals("Yellow", tshirtViewModels.get(0).getColor());

        tshirtViewModels = manageInventoryService.findTshirtsBySize("XL");
        assertEquals("XL", tshirtViewModels.get(0).getSize());

    }
}
