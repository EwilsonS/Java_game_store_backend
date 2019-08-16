package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.Tshirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TshirtDaoJdbcTemplateImpl implements TshirtDao {

    // Init Jdbc
    private JdbcTemplate jdbcTemplate;

    // PreparedsStatements
    private static final String INSERT_TSHIRT_SQL =
            "insert into t_shirt (size, color, description, price, quantity) values(?,?,?,?,?)";

    private static final String SELECT_TSHIRT_SQL =
            "select * from t_shirt where t_shirt_id=?";

    private static final String SELECT_ALL_TSHIRTS_SQL =
            "select * from t_shirt";

    private static final String UPDATE_TSHIRT_SQL =
            "update t_shirt set size=?, color=?, description=?, price=?, quantity=? where t_shirt_id=?";

    private static final String DELETE_TSHIRT_SQL =
            "delete from t_shirt where t_shirt_id=?";

    private static final String SELECT_TSHIRTS_BY_SIZE_SQL =
            "select * from t_shirt where size=?";

    private static final String SELECT_TSHIRTS_BY_COLOR_SQL =
            "select * from t_shirt where color=?";

    // Contructor injection+
    @Autowired
    public TshirtDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Interface implementation
    @Override
    @Transactional
    public Tshirt createTshirt(Tshirt tshirt) {
        jdbcTemplate.update(
                INSERT_TSHIRT_SQL,
                tshirt.getSize(),
                tshirt.getColor(),
                tshirt.getDescription(),
                tshirt.getPrice(),
                tshirt.getQuantity()
                );
        tshirt.setT_shirt_id(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return tshirt;
    }

    @Override
    public Tshirt getTshirtById(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_TSHIRT_SQL, this::mapRowToTshirt, id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Tshirt> getAllTshirts() {
        try{
            return jdbcTemplate.query(SELECT_ALL_TSHIRTS_SQL, this::mapRowToTshirt);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void updateTshirt(Tshirt tshirt) {
        jdbcTemplate.update(
                UPDATE_TSHIRT_SQL,
                tshirt.getSize(),
                tshirt.getColor(),
                tshirt.getDescription(),
                tshirt.getPrice(),
                tshirt.getQuantity(),
                tshirt.getT_shirt_id()
        );
    }

    @Override
    public void deleteTshirt(int id) {
        jdbcTemplate.update(DELETE_TSHIRT_SQL, id);
    }

    @Override
    public List<Tshirt> findTshirtsByColor(String color) {
        try{
            return jdbcTemplate.query(SELECT_TSHIRTS_BY_COLOR_SQL, this::mapRowToTshirt, color);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Tshirt> findTshirtsBySize(String size) {
        try{
            return jdbcTemplate.query(SELECT_TSHIRTS_BY_SIZE_SQL, this::mapRowToTshirt, size);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    // Helper method to map properties to rows
    private Tshirt mapRowToTshirt(ResultSet rs, int rowNum) throws SQLException {
        Tshirt tshirt = new Tshirt();
        tshirt.setT_shirt_id(rs.getInt("t_shirt_id"));
        tshirt.setSize(rs.getString("size"));
        tshirt.setColor(rs.getString("color"));
        tshirt.setDescription(rs.getString("description"));
        tshirt.setPrice(rs.getBigDecimal("price"));
        tshirt.setQuantity(rs.getInt("quantity"));
        return tshirt;
    }
}
