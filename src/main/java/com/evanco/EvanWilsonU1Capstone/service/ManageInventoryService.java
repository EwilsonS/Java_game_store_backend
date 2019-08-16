package com.evanco.EvanWilsonU1Capstone.service;

import com.evanco.EvanWilsonU1Capstone.dao.ConsoleDao;
import com.evanco.EvanWilsonU1Capstone.dao.GameDao;
import com.evanco.EvanWilsonU1Capstone.dao.TshirtDao;
import com.evanco.EvanWilsonU1Capstone.model.Console;
import com.evanco.EvanWilsonU1Capstone.model.Game;
import com.evanco.EvanWilsonU1Capstone.model.Tshirt;
import com.evanco.EvanWilsonU1Capstone.viewmodel.ConsoleViewModel;
import com.evanco.EvanWilsonU1Capstone.viewmodel.GameViewModel;
import com.evanco.EvanWilsonU1Capstone.viewmodel.TshirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManageInventoryService {
    // Field injection
    GameDao gameDao;
    ConsoleDao consoleDao;
    TshirtDao tshirtDao;

    // Constructor injection
    @Autowired
    public ManageInventoryService(GameDao gameDao, ConsoleDao consoleDao, TshirtDao tshirtDao){
        this.gameDao = gameDao;
        this.consoleDao = consoleDao;
        this.tshirtDao = tshirtDao;
    }

    // -------------------------------------------------------
    // Service methods
    // -------------------------------------------------------

    // game
    @Transactional
    public GameViewModel saveGame(GameViewModel gvm) {
        Game game = new Game();
        game.setTitle(gvm.getTitle());
        game.setEsrb_rating(gvm.getEsrb_rating());
        game.setDescription(gvm.getDescription());
        game.setPrice(gvm.getPrice());
        game.setStudio(gvm.getStudio());
        game.setQuantity(gvm.getQuantity());
        game = gameDao.createGame(game);

        gvm.setGame_id(game.getGame_id());
        return gvm;
    }

    public GameViewModel findGameById(int id) {
        Game game = gameDao.getGameById(id);
        if (game != null) {
            return buildGameViewModel(game);
        }
        return null;
    }

    public List<GameViewModel> findAllGames() {
        List<Game> games = gameDao.getAllGames();
        List<GameViewModel> gameViewModels = new ArrayList<>();
        for (Game game : games) {
            GameViewModel gvm = buildGameViewModel(game);
            gameViewModels.add(gvm);
        }
        return gameViewModels;
    }

    @ Transactional
    public void updateGame(GameViewModel gvm) {
        Game game = gameDao.getGameById(gvm.getGame_id());
        game.setGame_id(gvm.getGame_id());
        if(gvm.getTitle() != null){
            game.setTitle(gvm.getTitle());
        }
        if(gvm.getEsrb_rating() != null){
            game.setEsrb_rating(gvm.getEsrb_rating());
        }
        if(gvm.getDescription() != null) {
            game.setDescription(gvm.getDescription());
        }
        if(gvm.getPrice() != null) {
            game.setPrice(gvm.getPrice());
        }
        if(gvm.getStudio() != null){
            game.setStudio(gvm.getStudio());
        }
        if(gvm.getQuantity() != 0 ){
            game.setQuantity(gvm.getQuantity());
        }

        gameDao.updateGame(game);
    }

    public void deleteGame(int id) {
        gameDao.deleteGame(id);
    }

    public List<GameViewModel> findGamesByTitle(String title){
        List<Game> games = gameDao.findGamesByTitle(title);
        List<GameViewModel> gameViewModels = new ArrayList<>();
        for (Game game : games) {
            GameViewModel gvm = buildGameViewModel(game);
            gameViewModels.add(gvm);
        }
        return gameViewModels;
    }

    public List<GameViewModel> findGamesByRating(String rating){
        List<Game> games = gameDao.findGamesByRating(rating);
        List<GameViewModel> gameViewModels = new ArrayList<>();
        for (Game game : games) {
            GameViewModel gvm = buildGameViewModel(game);
            gameViewModels.add(gvm);
        }
        return gameViewModels;
    }

    public List<GameViewModel> findGamesByStudio(String studio){
        List<Game> games = gameDao.findGamesByStudio(studio);
        List<GameViewModel> gameViewModels = new ArrayList<>();
        for (Game game : games) {
            GameViewModel gvm = buildGameViewModel(game);
            gameViewModels.add(gvm);
        }
        return gameViewModels;
    }


    // console
    @Transactional
    public ConsoleViewModel saveConsole(ConsoleViewModel cvm) {
        Console console = new Console();
        console.setConsole_id(cvm.getConsole_id());
        console.setModel(cvm.getModel());
        console.setManufacturer(cvm.getManufacturer());
        console.setMemory_amount(cvm.getMemory_amount());
        console.setProcessor(cvm.getProcessor());
        console.setPrice(cvm.getPrice());
        console.setQuantity(cvm.getQuantity());
        console = consoleDao.createConsole(console);

        cvm.setConsole_id(console.getConsole_id());
        return cvm;
    }

    public ConsoleViewModel findConsoleById(int id) {
        Console console = consoleDao.getConsoleById(id);
        if(console != null){
            return buildConsoleViewModel(console);
        }
        return null;
    }

    public List<ConsoleViewModel> findAllConsoles() {
        List<Console> consoles = consoleDao.getAllConsoles();
        List<ConsoleViewModel> consoleViewModels = new ArrayList<>();
        for (Console console: consoles){
            ConsoleViewModel cvm = buildConsoleViewModel(console);
            consoleViewModels.add(cvm);
        }
        return consoleViewModels;
    }

    @Transactional
    public void updateConsole(ConsoleViewModel cvm) {
        Console console = consoleDao.getConsoleById(cvm.getConsole_id());
        console.setConsole_id(cvm.getConsole_id());
       if(cvm.getModel() != null){
           console.setModel(cvm.getModel());
       }
       if (cvm.getManufacturer() != null){
           console.setManufacturer(cvm.getManufacturer());
       }
       if(cvm.getMemory_amount() != null){
           console.setMemory_amount(cvm.getMemory_amount().toString());
       }
       if(cvm.getProcessor() != null){
           console.setProcessor(cvm.getProcessor());
       }
       if(cvm.getPrice() != null){
           console.setPrice(cvm.getPrice());
       }
       if(cvm.getQuantity() != 0){
           console.setQuantity(cvm.getQuantity());
       }
        consoleDao.updateConsole(console);
    }

    public void deleteConsole(int id) {
        consoleDao.deleteConsole(id);
    }

    public List<ConsoleViewModel> findConsolesByMfr(String mfr){
        List<Console> consoles = consoleDao.findConsolesByManufacturer(mfr);
        List<ConsoleViewModel> consoleViewModels = new ArrayList<>();
        for (Console console: consoles){
            ConsoleViewModel cvm = buildConsoleViewModel(console);
            consoleViewModels.add(cvm);
        }
        return consoleViewModels;

    }


    // tshirt
    @Transactional
    public TshirtViewModel saveTshirt(TshirtViewModel tvm) {
        Tshirt tshirt = new Tshirt();
        tshirt.setT_shirt_id(tvm.getT_shirt_id());
        tshirt.setSize(tvm.getSize());
        tshirt.setColor(tvm.getColor());
        tshirt.setDescription(tvm.getDescription());
        tshirt.setPrice(tvm.getPrice());
        tshirt.setQuantity(tvm.getQuantity());
        tshirt = tshirtDao.createTshirt(tshirt);

        tvm.setT_shirt_id(tshirt.getT_shirt_id());
        return tvm;
    }

    public TshirtViewModel findTshirtById(int id) {
        Tshirt tshirt = tshirtDao.getTshirtById(id);
        if(tshirt != null){
            return buildTshirtViewModel(tshirt);
        }
        return null;
    }

    public List<TshirtViewModel> findAllTshirts() {
        List<Tshirt> tshirts = tshirtDao.getAllTshirts();
        List<TshirtViewModel> tshirtViewModels = new ArrayList<>();
        for(Tshirt tshirt: tshirts){
            TshirtViewModel tvm = buildTshirtViewModel(tshirt);
            tshirtViewModels.add(tvm);
        }
        return tshirtViewModels;
    }

    @Transactional
    public void updateTshirt(TshirtViewModel tvm) {
        Tshirt tshirt = tshirtDao.getTshirtById(tvm.getT_shirt_id());
        tshirt.setT_shirt_id(tvm.getT_shirt_id());
        if(tvm.getSize() != null){
            tshirt.setSize(tvm.getSize());
        }
        if(tvm.getColor() != null){
            tshirt.setColor(tvm.getColor());
        }
        if(tvm.getDescription() != null){
            tshirt.setDescription(tvm.getDescription());
        }
        if(tvm.getPrice() != null){
            tshirt.setPrice(tvm.getPrice());
        }
        if (tvm.getQuantity() != 0){
            tshirt.setQuantity(tvm.getQuantity());
        }

        tshirtDao.updateTshirt(tshirt);
    }

    public void deleteTshirt(int id) {
        tshirtDao.deleteTshirt(id);
    }

    public List<TshirtViewModel> findTshirtsBySize(String size) {
        List<Tshirt> tshirts = tshirtDao.findTshirtsBySize(size);
        List<TshirtViewModel> tshirtViewModels = new ArrayList<>();
        for(Tshirt tshirt: tshirts){
            TshirtViewModel tvm = buildTshirtViewModel(tshirt);
            tshirtViewModels.add(tvm);
        }
        return tshirtViewModels;
    }

    public List<TshirtViewModel> findTshirtsByColor(String color) {
        List<Tshirt> tshirts = tshirtDao.findTshirtsByColor(color);
        List<TshirtViewModel> tshirtViewModels = new ArrayList<>();
        for(Tshirt tshirt: tshirts){
            TshirtViewModel tvm = buildTshirtViewModel(tshirt);
            tshirtViewModels.add(tvm);
        }
        return tshirtViewModels;
    }

    // -------------------------------------------------------
    // BuildViewModel helper methods
    // -------------------------------------------------------
    private GameViewModel buildGameViewModel(Game game) {
        GameViewModel gvm = new GameViewModel();
        gvm.setGame_id(game.getGame_id());
        gvm.setTitle(game.getTitle());
        gvm.setEsrb_rating(game.getEsrb_rating());
        gvm.setDescription(game.getDescription());
        gvm.setPrice(game.getPrice());
        gvm.setStudio(game.getStudio());
        gvm.setQuantity(game.getQuantity());
        return gvm;
    }

    private ConsoleViewModel buildConsoleViewModel(Console console) {
        ConsoleViewModel cvm = new ConsoleViewModel();
        cvm.setConsole_id(console.getConsole_id());
        cvm.setModel(console.getModel());
        cvm.setManufacturer(console.getManufacturer());
        cvm.setMemory_amount(console.getMemory_amount());
        cvm.setProcessor(console.getProcessor());
        cvm.setPrice(console.getPrice());
        cvm.setQuantity(console.getQuantity());
        return cvm;
    }

    private TshirtViewModel buildTshirtViewModel(Tshirt tshirt) {
        TshirtViewModel tvm = new TshirtViewModel();
        tvm.setT_shirt_id(tshirt.getT_shirt_id());
        tvm.setSize(tshirt.getSize());
        tvm.setColor(tshirt.getColor());
        tvm.setDescription(tshirt.getDescription());
        tvm.setPrice(tshirt.getPrice());
        tvm.setQuantity(tshirt.getQuantity());
        return tvm;

    }
}
