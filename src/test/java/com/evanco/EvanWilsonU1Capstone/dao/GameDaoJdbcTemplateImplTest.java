package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.Game;
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
public class GameDaoJdbcTemplateImplTest {

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
    ProcessingFeeDao processingFeeDao;
    @Autowired
    SalesTaxDao salesTaxDao;

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
    public void addGetDeleteGetAllGames(){
        Game game = new Game();
        game.setTitle("Running Around");
        game.setEsrb_rating("E");
        game.setDescription("Adventure game for anyone");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("LA Gaming");
        game.setQuantity(25);

        // Add
        game = gameDao.createGame(game);

        // Get
        Game game2 = gameDao.getGameById(game.getGame_id());
        assertEquals(game, game2);

        // Delete
        gameDao.deleteGame(game.getGame_id());
        assertNull(gameDao.getGameById(game.getGame_id()));

        // Get All
        gameDao.createGame(game);
        assertEquals(1, gameDao.getAllGames().size());
    }

    @Test
    public void updateGame(){

        Game game = new Game();
        game.setTitle("Running Around");
        game.setEsrb_rating("E");
        game.setDescription("Adventure game for anyone");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("LA Gaming");
        game.setQuantity(25);

        // Add
        game = gameDao.createGame(game);

        // Update
        game.setTitle("UPDATE");
        gameDao.updateGame(game);

        // Get
        Game game2 = gameDao.getGameById(game.getGame_id());
        assertEquals(game, game2);

    }

    @Test
    public void findGamesByTitleTest(){
        // Add 2 games with title "ONE"
        Game game = new Game();
        game.setTitle("ONE");
        game.setEsrb_rating("E");
        game.setDescription("Adventure game for anyone");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("LA Gaming");
        game.setQuantity(25);
        game = gameDao.createGame(game);
        game = gameDao.createGame(game);

        // Add 3 games with title "TWO"
        game = new Game();
        game.setTitle("TWO");
        game.setEsrb_rating("E");
        game.setDescription("Adventure game for anyone");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("LA Gaming");
        game.setQuantity(25);
        game = gameDao.createGame(game);
        game = gameDao.createGame(game);
        game = gameDao.createGame(game);

        // Get all games
        assertEquals(5, gameDao.getAllGames().size());

        // Get games by title
        assertEquals(2, gameDao.findGamesByTitle("ONE").size());
    }

    @Test
    public void findGamesByRatingTest(){
        // Add 2 games with rating "ONE"
        Game game = new Game();
        game.setTitle("title");
        game.setEsrb_rating("ONE");
        game.setDescription("Adventure game for anyone");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("LA Gaming");
        game.setQuantity(25);
        game = gameDao.createGame(game);
        game = gameDao.createGame(game);

        // Add 3 games with rating "TWO"
        game = new Game();
        game.setTitle("title");
        game.setEsrb_rating("TWO");
        game.setDescription("Adventure game for anyone");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("LA Gaming");
        game.setQuantity(25);
        game = gameDao.createGame(game);
        game = gameDao.createGame(game);
        game = gameDao.createGame(game);

        // Get all games
        assertEquals(5, gameDao.getAllGames().size());

        // Get games by title
        assertEquals(2, gameDao.findGamesByRating("ONE").size());
    }

    @Test
    public void findGamesByStudio(){
        // Add 2 games with studio "ONE"
        Game game = new Game();
        game.setTitle("title");
        game.setEsrb_rating("E");
        game.setDescription("Adventure game for anyone");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("ONE");
        game.setQuantity(25);
        game = gameDao.createGame(game);
        game = gameDao.createGame(game);

        // Add 3 games with studio "TWO"
        game = new Game();
        game.setTitle("title");
        game.setEsrb_rating("TWO");
        game.setDescription("Adventure game for anyone");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("TWO");
        game.setQuantity(25);
        game = gameDao.createGame(game);
        game = gameDao.createGame(game);
        game = gameDao.createGame(game);

        // Get all games
        assertEquals(5, gameDao.getAllGames().size());

        // Get games by title
        assertEquals(2, gameDao.findGamesByStudio("ONE").size());

    }
}
