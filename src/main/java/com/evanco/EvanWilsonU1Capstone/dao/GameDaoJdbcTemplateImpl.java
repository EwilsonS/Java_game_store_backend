package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDaoJdbcTemplateImpl implements GameDao {

    // Init Jdbc
    private JdbcTemplate jdbcTemplate;
    // Prepared statements
    private static final String INSERT_GAME_SQL =
            "insert into game (title, esrb_rating, description, price, studio, quantity) values(?,?,?,?,?,?)";

    private static final String SELECT_GAME_SQL =
            "select * from game where game_id=?";

    private static final String SELECT_ALL_GAMES_SQL =
            "select * from game";

    private static final String UPDATE_GAME_SQL =
            "update game set title=?, esrb_rating=?, description=?, price=?, studio=?, quantity=? where game_id=?";

    private static final String DELETE_GAME_SQL =
            "delete from game where game_id=?";

    private static final String SELECT_GAMES_BY_STUDIO_SQL =
            "select * from game where studio=?";

    private static final String SELECT_GAMES_BY_ESRB_RATING_SQL =
            "select * from game where esrb_rating=?";

    private static final String SELECT_GAMES_TITLE_SQL =
            "select * from game where title=?";



    // Constructor injection
    @Autowired
    public GameDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Interface implementation
    @Override
    @Transactional
    public Game createGame(Game game) {
        jdbcTemplate.update(
                INSERT_GAME_SQL,
                game.getTitle(),
                game.getEsrb_rating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity()
                );

        game.setGame_id(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        return game;
    }

    @Override
    public Game getGameById(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_GAME_SQL, this::mapRowToGame, id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Game> getAllGames() {
        return jdbcTemplate.query(SELECT_ALL_GAMES_SQL, this::mapRowToGame);
    }

    @Override
    public void updateGame(Game game) {
        jdbcTemplate.update(UPDATE_GAME_SQL,
                game.getTitle(),
                game.getEsrb_rating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity(),
                game.getGame_id()
                );
    }

    @Override
    public void deleteGame(int id) {
        jdbcTemplate.update(DELETE_GAME_SQL, id);
    }

    @Override
    public List<Game> findGamesByTitle(String title) {
        try{
            return jdbcTemplate.query(SELECT_GAMES_TITLE_SQL, this::mapRowToGame, title);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Game> findGamesByRating(String esrb_rating) {
        try{
            return jdbcTemplate.query(SELECT_GAMES_BY_ESRB_RATING_SQL, this::mapRowToGame, esrb_rating);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Game> findGamesByStudio(String studio) {
        try{
            return jdbcTemplate.query(SELECT_GAMES_BY_STUDIO_SQL, this::mapRowToGame, studio);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    // Helper method to map properties to rows
    private Game mapRowToGame(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();
        game.setGame_id(rs.getInt("game_id"));
        game.setTitle(rs.getString("title"));
        game.setEsrb_rating(rs.getString("esrb_rating"));
        game.setDescription(rs.getString("description"));
        game.setPrice(rs.getBigDecimal("price"));
        game.setStudio(rs.getString("studio"));
        game.setQuantity(rs.getInt("quantity"));
        return game;
    }
}
