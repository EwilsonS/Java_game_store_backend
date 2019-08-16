package com.evanco.EvanWilsonU1Capstone.dao;

import java.math.BigDecimal;

public interface ProcessingFeeDao {

    /**
     * Retrieve processing fee and fill invoice field. This is not to be included with taxes.
     * Consoles: 14.99
     * T-Shirts: 1.98
     * Games: 1.49
     * If order quantity of any item is > 10, include additional $15.49
     * @param productType string populated from Order view Model.
     * @return cost type BigDecimal based on type
     */
    BigDecimal processingFee(String productType);
}
