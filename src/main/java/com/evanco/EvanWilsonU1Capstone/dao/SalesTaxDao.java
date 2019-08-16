package com.evanco.EvanWilsonU1Capstone.dao;

import com.evanco.EvanWilsonU1Capstone.model.SalesTaxRate;

import java.math.BigDecimal;
import java.util.List;

public interface SalesTaxDao {
    /**
     * calculateTax will be implemented in the InvoiceDaoJdbcTemplateImpl. It takes in a state
     * passed in from the Order View Model
     * @param state type string validate 2 characters only
     * @return tax decimal from db based on the state. (data already provided and never manipulated)
     */
    BigDecimal calculateTax(String state);

    List<SalesTaxRate> getAllStates();
}
