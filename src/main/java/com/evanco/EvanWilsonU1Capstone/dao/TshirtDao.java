package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.Tshirt;

import java.util.List;

/**
 * Tshirt Dao methods use standard CRUD operations. Nothing fancy going on here.
 * note: the first item in the method signature is its return type.
 */
public interface TshirtDao {

    Tshirt createTshirt(Tshirt tshirt);

    Tshirt getTshirtById(int id);

    List<Tshirt> getAllTshirts();

    void updateTshirt(Tshirt tshirt);

    void deleteTshirt(int id);

    List<Tshirt> findTshirtsByColor(String color);

    List<Tshirt> findTshirtsBySize(String size);

}
