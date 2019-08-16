package com.evanco.EvanWilsonU1Capstone.controller;

import com.evanco.EvanWilsonU1Capstone.exception.NotFoundException;
import com.evanco.EvanWilsonU1Capstone.service.ManageInventoryService;
import com.evanco.EvanWilsonU1Capstone.viewmodel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    ManageInventoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel createGame(@RequestBody @Valid GameViewModel game){
        return service.saveGame(game);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGames() {
        return service.findAllGames();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGame(@PathVariable("id") int id) {
        if(service.findGameById(id) == null){
            throw new NotFoundException("Game not found for id: " + id);
        }
        return service.findGameById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  updateGame(@PathVariable("id") int id, @RequestBody GameViewModel gvm){
        if(gvm.getGame_id() == 0){
            gvm.setGame_id(id);
        }
        if(id != gvm.getGame_id()){
            throw new IllegalArgumentException("Id on path must match Game id");
        }
        service.updateGame(gvm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable("id") int id){
        service.deleteGame(id);
    }

    @GetMapping("/title?{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByTitle(@PathVariable("title") String title){
        if(service.findGamesByTitle(title).size() == 0){
            throw new NotFoundException("Sorry there are no games with the title: " + title);
        }
        return service.findGamesByTitle(title);
    }
    @GetMapping("/esrb_rating?{esrb}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByRating(@PathVariable("esrb") String esrb){
        if(service.findGamesByRating(esrb).size() == 0){
            throw new NotFoundException("Sorry there are no games with an esrb rating of: " + esrb);
        }
        return service.findGamesByRating(esrb);
    }
    @GetMapping("/studio?{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByStudio(@PathVariable("studio") String studio){
        if(service.findGamesByStudio(studio).size() == 0){
            throw new NotFoundException("Sorry there are no games from the studio: " + studio);
        }
        return service.findGamesByStudio(studio);
    }
}
