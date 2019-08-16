package com.evanco.EvanWilsonU1Capstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Tshirt {

    private int t_shirt_id;
    private String size;
    private String color;
    private String description;
    private BigDecimal price;
    private int quantity;

    public int getT_shirt_id() {
        return t_shirt_id;
    }

    public void setT_shirt_id(int t_shirt_id) {
        this.t_shirt_id = t_shirt_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tshirt tshirt = (Tshirt) o;
        return getT_shirt_id() == tshirt.getT_shirt_id() &&
                getQuantity() == tshirt.getQuantity() &&
                getSize().equals(tshirt.getSize()) &&
                getColor().equals(tshirt.getColor()) &&
                getDescription().equals(tshirt.getDescription()) &&
                getPrice().equals(tshirt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getT_shirt_id(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }
}
