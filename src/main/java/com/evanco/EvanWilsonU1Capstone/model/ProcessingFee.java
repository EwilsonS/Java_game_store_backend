package com.evanco.EvanWilsonU1Capstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ProcessingFee {
    private String product_type;
    private BigDecimal fee;

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessingFee that = (ProcessingFee) o;
        return getProduct_type().equals(that.getProduct_type()) &&
                getFee().equals(that.getFee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct_type(), getFee());
    }
}
