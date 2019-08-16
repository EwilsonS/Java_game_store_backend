package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConsoleDaoJdbcTemplateImpl implements ConsoleDao {

    // Init Jdbc
    private JdbcTemplate jdbcTemplate;

    // Prepared statements
    private static final String INSERT_CONSOLE_SQL =
            "insert into console (model, manufacturer, memory_amount, processor, price, quantity) " +
                    "values (?,?,?,?,?,?)";

    private static final String SELECT_CONSOLE_SQL =
            "select * from console where console_id = ?";

    private static final String SELECT_ALL_CONSOLES_SQL =
            "select * from console";

    private static final String UPDATE_CONSOLE_SQL =
            "update console set model=?, manufacturer=?, memory_amount=?, processor=?, price=?, quantity=? " +
                    "where console_id=?";

    private static final String DELETE_CONSOLE_SQL =
            "delete from console where console_id=?";

    private static final String SELECT_CONSOLES_BY_MANUFACTURER_SQL =
            "select * from console where manufacturer=?";

    // Constructor injection
    @Autowired
    public ConsoleDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Interface implementation
    @Override
    @Transactional
    public Console createConsole(Console console) {
        jdbcTemplate.update(
                INSERT_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemory_amount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity()
        );

        console.setConsole_id(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return console;
    }

    @Override
    public Console getConsoleById(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CONSOLE_SQL, this::mapRowToConsole, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Console> getAllConsoles() {
        try {
            return jdbcTemplate.query(SELECT_ALL_CONSOLES_SQL, this::mapRowToConsole);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateConsole(Console console) {
        jdbcTemplate.update(
                UPDATE_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemory_amount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity(),
                console.getConsole_id()
        );
    }

    @Override
    public void deleteConsole(int id) {
        jdbcTemplate.update(DELETE_CONSOLE_SQL, id);
    }

    @Override
    public List<Console> findConsolesByManufacturer(String manufacturer) {
        return jdbcTemplate.query(SELECT_CONSOLES_BY_MANUFACTURER_SQL, this::mapRowToConsole, manufacturer);
    }

    // Helper method to map properties to rows
    private Console mapRowToConsole(ResultSet rs, int rowNum) throws SQLException {
        Console console = new Console();
        console.setConsole_id(rs.getInt("console_id"));
        console.setModel(rs.getString("model"));
        console.setManufacturer(rs.getString("manufacturer"));
        console.setMemory_amount(rs.getString("memory_amount"));
        console.setProcessor(rs.getString("processor"));
        console.setPrice(rs.getBigDecimal("price"));
        console.setQuantity(rs.getInt("quantity"));
        return console;
    }
}
