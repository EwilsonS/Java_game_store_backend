package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.Game;

import java.util.List;

/**
 * Game Dao methods use standard CRUD operations. Nothing fancy going on here.
 * note: the first item in the method signature is its return type.
 */
public interface GameDao {

    Game createGame(Game game);

    Game getGameById(int id);

    List<Game> getAllGames();

    void updateGame(Game game);

    void deleteGame(int id);

    List<Game> findGamesByTitle(String title);

    List<Game> findGamesByRating(String esrb_rating);

    List<Game> findGamesByStudio(String studio);
}
