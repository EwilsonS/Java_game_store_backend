package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.Console;

import java.util.List;

/**
 * Console Dao methods use standard CRUD operations. Nothing fancy going on here.
 * note: the first item in the method signature is its return type.
 */
public interface ConsoleDao {

    Console createConsole(Console console);

    Console getConsoleById(int id);

    List<Console> getAllConsoles();

    void updateConsole(Console console);

    void deleteConsole(int id);

    List<Console> findConsolesByManufacturer(String manufacturer);

}
